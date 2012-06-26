# Note that this is designed to run on a chumby One"
DESCRIPTION = "Files necessary to construct a factory burner prion"
PACKAGE_ARCH = "all"

SRC_URI = "git://github.com/sutajiokousagi/factory-prion.git"

SRCREV = "c0064769073aac7f06983cc0452733f28a72af71"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
PR = "r0"

S = "${WORKDIR}/git/src"

do_compile() {
}

do_install() {
	install -d ${DEPLOY_DIR_IMAGE}/prion
	cp -r ${S}/* ${DEPLOY_DIR_IMAGE}/prion/

	install -d ${D}/usr/share/prion
	cp -r ${S}/* ${D}/usr/share/prion
}
