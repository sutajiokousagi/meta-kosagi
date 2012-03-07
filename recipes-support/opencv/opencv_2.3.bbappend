PR .= "-amend-r0"
EXTRA_OECONF += "--with-v4l --with-v4l2"
DEPENDS += "v4l-utils"

RDEPENDS_${PN} += "libv4l"
