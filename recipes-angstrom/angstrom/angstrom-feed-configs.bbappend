PR .= "append-r0"

KOSAGI_URI = "http://netv.bunnie-bar.com"
KOVAN_BASEPATH = "build/kovan-debug/LATEST"

do_compile_append() {
	mkdir -p ${S}/${sysconfdir}/opkg

	echo "src/gz ${MACHINE_ARCH} ${KOSAGI_URI}/${KOVAN_BASEPATH}/${MACHINE_ARCH}" >  ${S}/${sysconfdir}/opkg/${MACHINE_ARCH}-feed.conf
}
