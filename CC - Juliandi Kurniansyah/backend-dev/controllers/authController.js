const admin = require("firebase-admin");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");
require("dotenv").config();

const JWT_SECRET = process.env.JWT_SECRET;
const firebaseConfig = require("../config/firebase");

if (!admin.apps.length) {
  admin.initializeApp({
    credential: admin.credential.cert(firebaseConfig),
  });
}

const db = admin.firestore();

const register = async (req, res) => {
  const { email, password, fullName } = req.body;

  if (!email || !password || !fullName) {
    return res
      .status(400)
      .send({ error: "Email, password, and full name are required" });
  }

  try {
    const userRecord = await admin.auth().createUser({
      email: email,
      password: password, 
    });

    const hashedPassword = await bcrypt.hash(password, 10);
    const userData = {
      fullName,
      email: userRecord.email,
      createdAt: new Date(),
      passwordHash: hashedPassword,
    };

    const usersRef = db.collection("users");
    const userDoc = usersRef.doc(userRecord.uid);
    await userDoc.set(userData);

    res
      .status(201)
      .send({ uid: userRecord.uid, email: userRecord.email, fullName });
  } catch (error) {
    if (error.code === "auth/email-already-in-use") {
      return res.status(400).send({ error: "Email already in use" });
    }
    console.error(error);
    res.status(500).send({ error: "Failed to create user" });
  }
};

const login = async (req, res) => {
  const { email, password } = req.body;

  if (!email || !password) {
    return res.status(400).send({ error: "Email and password are required" });
  }

  try {
    const userRecord = await admin.auth().getUserByEmail(email);
    const usersRef = db.collection("users");
    const userDoc = usersRef.doc(userRecord.uid);
    const userData = await userDoc.get();

    if (!userData.exists) {
      return res.status(401).send({ error: "User not found" });
    }

    const hashedPassword = userData.data().passwordHash;
    const isValid = await bcrypt.compare(password, hashedPassword);

    if (!isValid) {
      return res.status(401).send({ error: "Invalid email or password" });
    }

    const token = jwt.sign(
      { uid: userRecord.uid, email: userRecord.email },
      JWT_SECRET,
      { expiresIn: "1h" }
    );

    res.status(200).send({ token });
  } catch (error) {
    console.error(error);
    res.status(400).send({ error: "Failed to login" });
  }
};

module.exports = {
  register,
  login,
  db, 
};
