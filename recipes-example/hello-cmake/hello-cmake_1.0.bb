SUMMARY = "Hello with cmake"
DESCRIPTION = "A test application to demostrate how to create a \
	recipe for cmake-based project."

SECTION = "examples"
PRIORITY = "optional"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "file://${P}.tgz"

inherit cmake

