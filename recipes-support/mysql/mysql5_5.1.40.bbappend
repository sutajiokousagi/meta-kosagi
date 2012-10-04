THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/${PN}"], d)}:"
PRINC := "${@int(PRINC) + 1}"

SRC_URI += "file://mysqld.service"

do_install_append() {
	mkdir -p ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/mysqld.service ${D}/lib/systemd/system/
}

FILES_${PN}-server += "/lib/systemd/system/mysqld.service"
