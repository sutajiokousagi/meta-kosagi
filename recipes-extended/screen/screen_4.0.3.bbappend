PRINC := "${@int(PRINC) + 1}"

SRC_URI = "${GNU_MIRROR}/screen/screen-${PV}.tar.gz;name=tarball \
           ${DEBIAN_MIRROR}/main/s/screen/screen_4.0.3-14.diff.gz;name=patch \
           file://configure.patch \
           file://fix-parallel-make.patch \
           ${@base_contains('DISTRO_FEATURES', 'pam', '${PAM_SRC_URI}', '', d)}"

SRC_URI[patch.md5sum] = "5960bdae6782ee9356b7e0e0a1fa7c19"
SRC_URI[patch.sha256sum] = "10acb274b2fb0bb7137a0d66e52fa0f18125bc5198c7a8d5af381b4b30636316"
