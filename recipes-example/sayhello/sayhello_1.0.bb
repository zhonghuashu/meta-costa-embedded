SUMMARY = "SayHello demo"
DESCRIPTION = "SayHello project used in Yocto demo"

# NOTE: Set the License according to the LICENSE file of your project
#       and then add LIC_FILES_CHKSUM accordingly
LICENSE = "CLOSED"

SRC_URI = "file://${P}.tgz"

DEPENDS += "libhello"
RDEPENDS:${PN} += "libhello"

do_install(){
   install -d ${D}/usr/bin
   install -m 0700 sayhello ${D}/usr/bin
}