DESCRIPTION = "Kovan utilities"
HOMEPAGE = "http://www.kosagi.com/"
AUTHOR = "bunnie"
LICENSE = "BSD"
PR = "r3"

SRC_URI = "git://github.com/bunnie/kovan-util.git"
SRCREV = "6956658f26c056041d7137d0633796bfeab57dec"
S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"

do_install() {
    install -d ${D}${sbindir}
    # fpga module devnode udev rules
    install -d ${D}${base_libdir}/udev/rules.d
    install -m 0644 ${S}/helpers/kovan-xilinx.rules ${D}${base_libdir}/udev/rules.d/45-kovan-xilinx.rules

    # FPGA configuration
    install -m 0755 ${S}/jtag-fpga-idcode ${D}${sbindir}
    install -m 0755 ${S}/fpga-config.py ${D}${sbindir}
    install -m 0644 ${S}/firmware/kovan-lx45.bit ${D}${base_libdir}/firmware/
    install -m 0644 ${S}/firmware/kovan-lx9.bit ${D}${base_libdir}/firmware/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/udev/rules.d/"
FILES_${PN} += "${base_libdir}/firmware/"


# need to install symlink .wants in /lib/systemd/system/basic.target.wants/
# and install the file itself in /lib/systemd/system/
