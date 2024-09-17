SUMMARY = "Timer test program"
DESCRIPTION = "Test performance for posix timer."

# NOTE: Set the License according to the LICENSE file of your project
#       and then add LIC_FILES_CHKSUM accordingly
LICENSE = "CLOSED"

SRC_URI = "file://${P}.tgz"
                
do_install(){
   install -d ${D}/usr/bin
   install -m 0700 timertest ${D}/usr/bin
}