local logger = require("mert/logger").logger
logger:info("A log message2")
logger:warn("A log message with keyword arguments2", { warning = "something happened" })


