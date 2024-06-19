const admin = require("firebase-admin");
const jwt = require("jsonwebtoken");
const db = admin.firestore();

const getChatHistory = async (req, res) => {
  // Verify JWT token
  const token = req.headers["authorization"];
  if (!token) {
    return res.status(401).send({ error: "Unauthorized" });
  }

  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    req.user = decoded; // Set req.user with the decoded user data
  } catch (error) {
    return res.status(401).send({ error: "Invalid token" });
  }

  // Retrieve chat history for a user
  const userId = req.user.uid;

  try {
    const conversationsRef = db.collection("conversations");
    const userConversationsRef = conversationsRef.where("userId", "==", userId);
    const userConversationsSnapshot = await userConversationsRef.get();
    const chatHistory = [];

    await Promise.all(
      userConversationsSnapshot.docs.map(async (conversationDoc) => {
        const conversationId = conversationDoc.id;
        const messagesRef = conversationDoc.ref.collection("messages");
        const messagesSnapshot = await messagesRef
          .orderBy("timestamp", "asc")
          .get();

        const messagesData = messagesSnapshot.docs.map((messageDoc) => ({
          ...messageDoc.data(),
          messageId: messageDoc.id,
        }));

        chatHistory.push({
          conversationId,
          messages: messagesData,
        });
      })
    );

    res.status(200).send(chatHistory);
  } catch (error) {
    res.status(500).send({ error: error.message });
  }
};

module.exports = {
  getChatHistory,
};
