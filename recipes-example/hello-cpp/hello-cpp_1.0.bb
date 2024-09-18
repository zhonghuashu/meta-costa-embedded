SUMMARY = "Hello with C++"
DESCRIPTION = "A test application to demostrate how to create a \
	recipe for C++ makefile-based project."
SECTION = "examples"
PRIORITY = "optional"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

# Error: doesn't have GNU_HASH (didn't pass LDFLAGS?) [ldflags]
TARGET_CC_ARCH += "${LDFLAGS}"

SRC_URI = "file://${P}.tgz"

EXTRA_OEMAKE = "'CC=${CC}' \
                'AR=${AR}' \
                'RANLIB=${RANLIB}' \
				'CFLAGS=${CFLAGS} -I${S}/.' \
				'LDFLAGS=${LDFLAGS}' \
				'BUILDDIR=${S}'"

do_install () {
	oe_runmake install DESTDIR=${D} BINDIR=${bindir} SBINDIR=${sbindir} \
		MANDIR=${mandir} INCLUEDIR=${includedir}
}

