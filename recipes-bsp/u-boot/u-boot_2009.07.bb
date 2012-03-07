require u-boot.inc

UBOOT_BINARY = "u-boot.bin"
UBOOT_IMAGE = "u-boot-${MACHINE}-${PV}-${PR}.bin"
UBOOT_SYMLINK = "u-boot-${MACHINE}.bin"

PV = "2009.07"
PR = "r3"

COMPATIBLE_MACHINE = "(kovan)"

SRC_URI = "git://github.com/sutajiokousagi/u-boot-2009.07-silvermoon.git;protocol=git;branch=${MACHINE} \
          "

SRCREV = "cc1c182acbb991caa28ab2bbd9632d992b091af0"

LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

S = "${WORKDIR}/git/src"
