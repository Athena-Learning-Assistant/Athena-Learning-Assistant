const express = require("express");
const { getProfile, updateProfile } = require('../controllers/profileController');
const  authenticateJWT  = require("../middlewares/authenticateJWT");

const router = express.Router();

router.get('/', authenticateJWT, getProfile);
router.patch('/', authenticateJWT, updateProfile);

module.exports = router;