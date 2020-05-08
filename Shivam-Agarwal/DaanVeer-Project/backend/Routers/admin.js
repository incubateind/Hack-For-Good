const express = require("express");
const Route = express.Router();
const AdminMongoosemodal = require("../Modals/Adminschema");
const Joi = require("@hapi/joi");
const bcrypt = require("bcryptjs");
const Jwt = require("jsonwebtoken");
const {accesstoken,refreshtoken,sendaccesstoken0,sendaccesstoken1,sendrefreshtoken} = require("../function/jwt");
const {isAuth} = require('../function/isAuth')




module.exports = Route;
