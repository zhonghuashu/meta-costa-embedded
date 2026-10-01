# Point the U-Boot source fetch to the local u-boot-socfpga mirror.
# This keeps the upstream recipe version and branch, but overrides the repo URL.
UBOOT_REPO = "git:///home/shu/github/u-boot-socfpga-2022.04"
UBOOT_PROT = "file"

SRCREV = "fda0d9176f375b7f9fa64fea4d9f4e6fe0b5d158"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://socfpga_sockit_v2022.04.cfg"
