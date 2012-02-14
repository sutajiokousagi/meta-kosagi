DESCRIPTION = "Silvermoon boot blobs - miscellaneous binary blobs"
HOMEPAGE = "http://www.kosagi.com/"
AUTHOR = "Sean Cross"
LICENSE = "BSD"

COMPATIBLE_MACHINE = "kovan"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378"

SRC_URI = "file://obm.bin"
S = "${WORKDIR}"

PACKAGE_ARCH = "${MACHINE}"

do_compile() {
    true
}

# obm.bin is a ROM, with no gnu_hash.
INSANE_SKIP_${PN} = True

FILES_${PN} = "/boot"

do_install() {
    install -d ${D}/boot
    install -m 0755 ${S}/obm.bin ${D}/boot/obm.bin
}

do_runstrip() {
    true
}

# Copy the resulting file to the image directory
do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}
    install ${S}/obm.bin ${DEPLOY_DIR_IMAGE}/obm.bin
}
do_deploy[dirs] = "${S}"
addtask deploy before do_package_stage after do_compile
