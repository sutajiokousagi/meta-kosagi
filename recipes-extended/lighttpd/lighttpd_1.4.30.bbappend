PRINC := "${@int(PRINC) + 1}"

do_install_append() {
    rm -rf ${D}/www/logs
    ln -sf /tmp ${D}/www/logs
}
