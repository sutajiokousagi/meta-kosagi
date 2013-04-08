DESCRIPTION = "Units to initialize usb gadgets"

PR = "r0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

RDEPENDS_${PN} = "tor iptables dnsmasq-noinit"

COMPATIBLE_MACHINE = "(novena)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://masquerade.conf \
           file://masquerade.service \
           file://network-eth0.service \
           file://network-eth1.service \
          "

do_install() {
    install -d ${D}${base_libdir}/systemd/system/
    install -m 0644 ${WORKDIR}/*.service ${D}${base_libdir}/systemd/system

    install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
    ln -sf ../masquerade.service ${D}${base_libdir}/systemd/system/basic.target.wants/

    install -d ${D}${sysconfdir}/sysctl.d
    install -m 0644 ${WORKDIR}/masquerade.conf ${D}${sysconfdir}/sysctl.d
}

FILES_${PN} = "${sysconfdir} ${base_libdir}"
