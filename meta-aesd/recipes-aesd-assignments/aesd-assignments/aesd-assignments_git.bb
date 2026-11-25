# Builds and installs the aesdsocket server from the aesd-assignments repo,
# plus an init script that starts it at boot and stops it at shutdown.
# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/hanpov/aesd-assignments.git;protocol=ssh;branch=master"

PV = "1.0+git${SRCPV}"
SRCREV = "c3927252dc50f8d6bf235fe7ff7ce1f600962d4f"

FILES:${PN} += "${bindir}/aesdsocket"
FILES:${PN} += "${sysconfdir}/init.d/aesdsocket-start-stop"

TARGET_LDFLAGS += "-pthread -lrt"

# Register the init script with sysvinit (start at boot, stop at shutdown)
inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop"

do_configure () {
	:
}

do_compile () {
	oe_runmake -C ${S}/server
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/server/aesdsocket ${D}${bindir}/
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/server/aesdsocket-start-stop ${D}${sysconfdir}/init.d/
}
