SUMMARY = "Hello demo library"
DESCRIPTION = "Hello shared library used in Yocto demo"

# NOTE: Set the License according to the LICENSE file of your project
#       and then add LIC_FILES_CHKSUM accordingly
LICENSE = "CLOSED"

SRC_URI = "file://${P}.tgz"

do_install(){
   install -d ${D}${includedir}
   install -d ${D}${libdir}

   install hellolib.h ${D}${includedir}
   oe_soinstall ${PN}.so.${PV} ${D}${libdir}
}