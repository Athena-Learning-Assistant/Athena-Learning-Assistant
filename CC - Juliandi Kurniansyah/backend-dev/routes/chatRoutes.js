const express = require('express');
const { getChatHistory } = require('../controllers/chatController');
const authenticateJWT = require('../middlewares/authenticateJWT');

const router = express.Router();

router.get('/history', authenticateJWT, getChatHistory);

module.exports = router;