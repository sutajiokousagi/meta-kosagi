DESCRIPTION = "Kovan utilities"
HOMEPAGE = "http://www.kosagi.com/"
AUTHOR = "bunnie"
LICENSE = "BSD"
PR = "r4"

SRC_URI = "git://github.com/bunnie/kovan-util.git"
SRCREV = "6956658f26c056041d7137d0633796bfeab57dec"
S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"

do_compile() {
    cd ${S}
    ${CC} ${CFLAGS} ${LDFLAGS} src/jtag.c -o jtag-fpga-idcode
}

do_install() {
    install -d ${D}${sbindir}
    # fpga module devnode udev rules
    install -d ${D}${base_libdir}/udev/rules.d
    install -m 0644 ${S}/helpers/kovan-xilinx.rules ${D}${base_libdir}/udev/rules.d/45-kovan-xilinx.rules

    # FPGA configuration
    install -m 0755 ${S}/jtag-fpga-idcode ${D}${sbindir}
    install -m 0755 ${S}/fpga-config.py ${D}${sbindir}

    # FPGA firmware
    install -d ${D}${base_libdir}/firmware
    install -m 0644 ${S}/firmware/kovan-lx45.bit ${D}${base_libdir}/firmware/
    install -m 0644 ${S}/firmware/kovan-lx9.bit ${D}${base_libdir}/firmware/

    #systemd rules
    install -d ${D}${base_libdir}/systemd
    install -d ${D}${base_libdir}/systemd/system
    install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
    install -m 0644 ${S}/helpers/kovan_xilinx.service ${D}${base_libdir}/systemd/system/
    ln -sf ../kovan_xilinx.service ${D}${base_libdir}/systemd/system/basic.target.wants/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/udev/rules.d/"
FILES_${PN} += "${base_libdir}/firmware/"
FILES_${PN} += "${base_libdir}/systemd"
FILES_${PN} += "${base_libdir}/systemd/system"
FILES_${PN} += "${base_libdir}/systemd/system/basic.target.wants"

