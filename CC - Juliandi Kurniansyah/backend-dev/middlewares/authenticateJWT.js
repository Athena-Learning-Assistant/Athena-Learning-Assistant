const jwt = require('jsonwebtoken');
require('dotenv').config();

const JWT_SECRET = process.env.JWT_SECRET;

const authenticateJWT = (req, res, next) => {
    const token = req.header('Authorization') ?.replace('Bearer ', '');
  
    console.log('Token:', token);
  
    if (!token) {
      return res.status(403).send({ error: 'A token is required for authentication' });
    }
  
    try {
      const decoded = jwt.verify(token, JWT_SECRET);
      console.log('Decoded user:', decoded);
      req.user = decoded;
    } catch (err) {
      console.error('Error verifying token:', err);
      return res.status(401).send({ error: 'Invalid Token' });
    }
    return next();
  };

module.exports = authenticateJWT;