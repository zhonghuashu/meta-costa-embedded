# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   COPYING
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://COPYING;md5=adefda309052235aa5d1e99ce7557010"

SRC_URI = "https://ftp.gnu.org/gnu/hello/hello-${PV}.tar.gz"
SRC_URI[md5sum] = "de3187eac06baf5f0506c06935a1fd29"
SRC_URI[sha1sum] = "0726a73f8e5ca2c336fae157a10929990d8f886b"
SRC_URI[sha256sum] = "429c779083c7c51ece716ae2f69e66caf383537a23119f9e200baf0f54c71c7a"
SRC_URI[sha384sum] = "58f7d5ae896cb6f836cf28b4976a106f51cc3176d91d5f2a561f8c9ecbc48b8a7fb473fa7e3d005a44d2fc9c6d238d15"
SRC_URI[sha512sum] = "e8379ba79bfaf8273d824a10a886de40868023a9f237f2eaf9d205968c4ff78edabeb1c0d880aa9e971ca8d39cdb5e9304218c677d65ae104a884b9047cefa75"

S = "${WORKDIR}/hello-${PV}"

# NOTE: if this software is not capable of being built in a separate build directory
# from the source, you should replace autotools with autotools-brokensep in the
# inherit line
inherit gettext autotools

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = ""

