const admin = require("firebase-admin");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");
require("dotenv").config();

const JWT_SECRET = process.env.JWT_SECRET;
const firebaseConfig = require("../config/firebase");
// Initialize Firebase Admin SDK
if (!admin.apps.length) {
  admin.initializeApp({
    credential: admin.credential.cert(firebaseConfig),
  });
}

const db = admin.firestore();

const getProfile = async (req, res) => {
  const { uid } = req.user;

  try {
    const usersRef = db.collection("users");
    const userDoc = usersRef.doc(uid);
    const userData = await userDoc.get(); // Get the Firestore document snapshot

    if (!userData.exists) {
      return res.status(404).send({ error: "User not found" });
    }

    const { fullName, email, createdAt } = userData.data();

    res.status(200).send({ fullName, email, createdAt });
  } catch (error) {
    console.error(error);
    res.status(500).send({ error: "Failed to retrieve user profile" });
  }
};

const updateProfile = async (req, res) => {
  const { uid } = req.user;
  const { fullName, email } = req.body;

  if (!fullName || !email) {
    return res.status(400).send({ error: "Full name and email are required" });
  }

  try {
    const usersRef = db.collection("users");
    const userDoc = usersRef.doc(uid);
    await userDoc.update({ fullName, email }); // Update the Firestore document

    res.status(200).send({ fullName, email });
  } catch (error) {
    console.error(error);
    res.status(500).send({ error: "Failed to update user profile" });
  }
};

module.exports = {
  getProfile,
  updateProfile,
};