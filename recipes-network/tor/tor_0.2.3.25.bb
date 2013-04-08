inherit autotools

DESCRIPTION = "Tor onion router server"
HOMEPAGE = "http://www.torproject.org/"
AUTHOR = "Sean Cross"
LICENSE = "BSD"
PR = "r0"

DEPENDS += "libevent"
RDEPENDS_${PN} += "libevent"

SRC_URI = "https://www.torproject.org/dist/tor-0.2.3.25.tar.gz \
           file://torrc \
           file://tor.service \
           "
LIC_FILES_CHKSUM = "file://LICENSE;md5=4b64317a982d939eca38140db46f2f8c"

do_install_append() {
	install -d ${D}${sysconfdir}/tor
	install -m 0644 ${WORKDIR}/torrc ${D}${sysconfdir}/tor/torrc

	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/tor.service ${D}${base_libdir}/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants
	ln -sf ../tor.service ${D}${base_libdir}/systemd/system/basic.target.wants/
}

FILES_${PN} += "${sysconfdir} ${base_libdir}/systemd/system"

SRC_URI[md5sum] = "a1c364189a9a66ed9daa8e6436489daf"
SRC_URI[sha256sum] = "bb2d6f1136f33e11d37e6e34184143bf191e59501613daf33ae3d6f78f3176a0"
