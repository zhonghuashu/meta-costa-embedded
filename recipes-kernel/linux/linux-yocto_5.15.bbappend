FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://yp_driver.cfg \
    file://0001-calibrate-Add-printk-example.patch \
    file://0002-Added-yocto-project-driver.patch"

