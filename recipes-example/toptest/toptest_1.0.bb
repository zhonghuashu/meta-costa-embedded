SUMMARY = "Top test program"
DESCRIPTION = "Test performance for top."

# NOTE: Set the License according to the LICENSE file of your project
#       and then add LIC_FILES_CHKSUM accordingly
LICENSE = "CLOSED"

SRC_URI = "file://${P}.tgz"

EXTRA_OEMAKE = "'CC=${CPP}' \
                'AR=${AR}' \
                'RANLIB=${RANLIB}' \
				'CFLAGS=${CFLAGS} -I${S}/.' \
				'LDFLAGS=${LDFLAGS}' \
				'BUILDDIR=${S}'"
                
do_install(){
   install -d ${D}/usr/bin
   install -m 0700 toptest ${D}/usr/bin
}