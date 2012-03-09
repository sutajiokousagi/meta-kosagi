PRINC := "${@int(PRINC) + 4}"
EXTRA_OECONF_append_kovan = "--enable-iwmmxt"
TARGET_CC_ARCH_kovan = "-march=iwmmxt -mtune=iwmmxt"
PACKAGE_ARCH = "${MACHINE_ARCH}"
