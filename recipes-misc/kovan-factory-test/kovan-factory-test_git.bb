inherit qt4x11

SRC_URI = "git://github.com/sutajiokousagi/kovan-test-gui.git \
           file://xorg.service \
           file://kovan-factory-test.service \
           file://factory.config"

SRCREV = "d58e46d9dbd1356d018d6b49395e5498864eba9b"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"
PR = "r4"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/xorg.service ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/kovan-factory-test.service ${D}/lib/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ../kovan-factory-test.service ${D}${base_libdir}/systemd/system/basic.target.wants/


	install -d ${D}/var/lib/connman
	install -m 0755 ${WORKDIR}/factory.config ${D}/var/lib/connman/

	install -d ${D}/usr/sbin
	install -m 0755 ${S}/kovan-test ${D}/usr/sbin/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/systemd"
FILES_${PN} += "/var/lib/connman"
