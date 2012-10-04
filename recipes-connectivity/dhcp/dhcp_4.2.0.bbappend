THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/${PN}"], d)}:"
PRINC := "${@int(PRINC) + 1}"

pkg_postinst_dhcp-client() {
    ln -sf /tmp ${localstatedir}/lib/dhcp
}

pkg_postrm_dhcp-client() {
    if [ "x$D" != "x" ]; then
        exit 1
    fi
    rm -rf ${localstatedir}/lib/dhcp
}
