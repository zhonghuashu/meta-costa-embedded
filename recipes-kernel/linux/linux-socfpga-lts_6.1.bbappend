# Point the kernel source fetch to the local linux-socfpga mirror.
# This keeps the upstream recipe version and branch, but overrides the repo URL.
KERNEL_REPO = "git:///home/shu/github/linux-socfpga-6.1.68"
KERNEL_PROT = "file"

SRCREV = "e337b07a64e99a85190473debe84d49e10661930"