# Note that this is designed to run on a chumby One"
DESCRIPTION = "Files necessary to construct a factory burner prion"
PACKAGE_ARCH = "all"

SRC_URI = "git://github.com/sutajiokousagi/factory-prion.git"

SRCREV = "249c742ba3993eb068a9788d52fe63b9cd16b970"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
PR = "r1"

S = "${WORKDIR}/git/src"

do_compile() {
}

do_install() {
	install -d ${DEPLOY_DIR_IMAGE}/prion
	cp -r ${S}/* ${DEPLOY_DIR_IMAGE}/prion/

	install -d ${D}/usr/share/prion
	cp -r ${S}/* ${D}/usr/share/prion
}
