This README file contains information on the contents of the meta-costa-embedded layer.

Please see the corresponding sections below for details.

Dependencies
============

  URI: <first dependency>
  branch: <branch name>

  URI: <second dependency>
  branch: <branch name>

Patches
=======

Please submit any patches against the meta-costa-embedded layer to the xxxx mailing list (xxxx@zzzz.org)
and cc: the maintainer:

Maintainer: XXX YYYYYY <xxx.yyyyyy@zzzzz.com>

Table of Contents
=================
## meta-costa-embedded
Customize embedded linux distribution using Yocto build.
- VS Code extension: Yocto Project BitBake
```shell
# Adding the meta-costa-embedded layer to your build
bitbake-layers add-layer meta-costa-embedded
```
## Setup host package
```shell
# For ubuntu-22.04
$ apt update
$ apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev python3-subunit mesa-common-dev zstd liblz4-tool file locales libacl1
```

## Build Poky Linux
- Refer to [Yocto Quick build](https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html)
- Refer to [Yocto构建流程](https://zhuanlan.zhihu.com/p/663983749)
```shell
$ mkdir ~/yocto && cd yocto
# Release 4.0 (kirkstone)
$ git clone -b kirkstone --depth=1 https://git.yoctoproject.org/poky
# Define Yocto Project’s build environment on your build host.
$ source poky/oe-init-build-env
# Build image with minimal size.
$ bitbake core-image-minimal
# Simulate Your Image Using QEMU.
# runqemu qemux86-64. Error: yocto Could not initialize SDL(x11 not available)
$ runqemu nographic
Poky (Yocto Project Reference Distro) 4.0.18 qemux86-64 /dev/ttyS0
qemux86-64 login:

# Exit QEMU
$ sudo killall qemu-system-x86_64

```

## Create own layer
- Refer to [定制Yocto系统](https://zhuanlan.zhihu.com/p/663983810)
```shell
$ source poky/oe-init-build-env

# Create own layer.
$ cd ~/yocto/example-yocto
$ bitbake-layers create-layer meta-costa-embedded

# Assess how compatible your layer is with the Yocto Project
# Note: meta-costa-embedded shall be removed from bblayers.conf.
$ yocto-check-layer /home/shu/yocto/example-yocto/meta-costa-embedded
INFO: Ran 7 tests in 175.368s
INFO: OK
INFO:  (skipped=2)
INFO: 
INFO: Summary of results:
INFO: 
INFO: meta-costa-embedded ... PASS

# Add created layer into Yocto project bblayers.conf.
$ cd ~/yocto/build
$ bitbake-layers add-layer ../example-yocto/meta-costa-embedded
NOTE: Starting bitbake server...
$ bitbake-layers show-layers
NOTE: Starting bitbake server...
layer                 path                                      priority
==========================================================================
meta                  /home/shu/yocto/poky/meta                 5
meta-poky             /home/shu/yocto/poky/meta-poky            5
meta-yocto-bsp        /home/shu/yocto/poky/meta-yocto-bsp       5
meta-costa-embedded     /home/shu/yocto/example-yocto/meta-costa-embedded  6

# Check if example can be run successful (~/yocto/build).
$ bitbake example
$ bitbake -c clean
$ bitbake -c build
***********************************************
*                                             *
*  Example recipe created by bitbake-layers   *
*                                             *
***********************************************
```

## Create own Linux distribution
- Refer to [定制Yocto系统](https://zhuanlan.zhihu.com/p/663983810)
- Customize distribution name in distro/costa-embedded.conf. 
- Change local.conf to DISTRO from "Poky" to "costa-embedded".
```shell
$ source poky/oe-init-build-env
# Yocto start rebuild using new Linux distribution.
$ bitbake example
$ bitbake core-image-minimal

# Start new Linux distribution in QEMU.
$ runqemu nographic
costa-embedded (Costa Embedded Linux by Yocto) 4.0.18 qemux86-64 /dev/ttyS0
qemux86-64 login: root
root@qemux86-64:~#
```
## Add OSS packages
- Refer to [添加包到镜像中](https://zhuanlan.zhihu.com/p/666675477)
```shell
# Append OSS dropbear to recipes-core/images/costa-embedded-image.bb
# Build image include appended package.
$ bitbake costa-embedded-image
# Start Linux distribution with dropbear server started.
$ runqemu nographic
Starting Dropbear SSH server: Generating 2048 bit rsa key
Starting syslogd/klogd: done
costa-embedded (Costa Embedded Linux by Yocto) 4.0.18 qemux86-64 /dev/ttyS0
```

## Add makefile project
- Build Makefile project `hello-make`
- Refer to [添加包到镜像中](https://zhuanlan.zhihu.com/p/666675477)
- Refer to [嵌入式Linux系统开发：基于Yocto Project](https://m.zhangyue.com/readbook/11865758/66.html?p2=111010&share=1&anchorId=) / ch 8.3.2
- Refer to [devtool Quick Reference](https://docs.yoctoproject.org/ref-manual/devtool-reference.html)

```shell
# 以目录hello-make-1.0前缀在包中的文件来建立正确的目录结构:
# hello-make-1.0\hello-make-1.0\, 解压后正好为：hello-make-1.0\*
tar --transform "s/^\./hello-make-1.0/" -czvf hello-make-1.0.tgz .
mv -f hello-make-1.0.tgz ~/yocto/example-yocto/meta-costa-embedded/recipes-example/hello-make/files
bitbake hello-make
bitbake costa-embedded-image

# Run hello-make in qemu device.
root@qemux86-64:~# hello-make
Hello, World! My first Yocto Project recipe.

root@qemux86-64:~# ls /usr/bin/hello-make -lt
-rwxr-xr-x    1 root     root         14376 Mar  9  2018 /usr/bin/hello-make
```

## Add cmake project
- Build Makefile project `hello-cmake`

```shell
tar --transform "s/^\./hello-cmake-1.0/" -czvf hello-cmake-1.0.tgz .
mv -f hello-cmake-1.0.tgz ~/yocto/example-yocto/meta-costa-embedded/recipes-example/hello-cmake/files
bitbake hello-cmake
bitbake costa-embedded-image

# Run hello-cmake in qemu device.
root@qemux86-64:~# hello-cmake
Hello, World! My first Yocto Project recipe.
root@qemux86-64:~# ls /usr/bin/hello-* -lt
-rwxr-xr-x    1 root     root         14376 Mar  9  2018 /usr/bin/hello-cmake
-rwxr-xr-x    1 root     root         14376 Mar  9  2018 /usr/bin/hello-make
```

## Add example recipes from Yocto manual
- Refer to [Write a new recipe - Examples](https://docs.yoctoproject.org/4.0.18/dev-manual/new-recipe.html#examples)
- Building a Single .c File Package: `helloworld`
- Building a Makefile-based package: `lz4`
- Building an Autotooled package: `hello-autotools`
```shell
recipetool create -o hello-autotools_2.3.bb https://ftp.gnu.org/gnu/hello/hello-2.3.tar.gz
bitbake helloworld
bitbake lz4
bitbake hello-autotools

```

## Add example sayhello from Yocto Project Concept
- Refer to [BitBake Task Map](https://docs.yoctoproject.org/overview-manual/concepts.html#bitbake-tasks-map)
- `sayhello` project depends on `libhello`
- BitBake Task Map see [SVG](https://docs.yoctoproject.org/_images/bitbake_tasks_map.svg)
```shell
tar --transform "s/^\./libhello-1.0/" -czvf libhello-1.0.tgz .
mv -f libhello-1.0.tgz ~/yocto/example-yocto/meta-costa-embedded/recipes-example/libhello/files

tar --transform "s/^\./sayhello-1.0/" -czvf sayhello-1.0.tgz .
mv -f sayhello-1.0.tgz ~/yocto/example-yocto/meta-costa-embedded/recipes-example/sayhello/files

# -v: Enable verbose output in additional to log file.
bitbake sayhello -v
bitbake costa-embedded-image

root@qemux86-64:~# sayhello
Hello from a Yocto demo
```
## Application development with devtool
- Refer to [Application Development with Extensible SDK](https://wiki.yoctoproject.org/wiki/Application_Development_with_Extensible_SDK)
```shell
# Generate recipe: /path/to/build/workspace/recipes/bbexample/
devtool add bbexample https://github.com/whbruce/bbexample.git
# Build
devtool build bbexample
# Run qumu / deploy bbexample to qemu
runqemu nographic
devtool deploy-target -s bbexample root@192.168.7.2
# Run 
root@qemux86-64:~# bbexample
Hello Yocto World...
Hello World (from a shared library!)
# Update and commit with message "Changed bbexample message"
# Publish recipes-bbexample with patch file 0001-Changed-bbexample-message.patch
devtool finish bbexample meta-costa-embedded
# Build image
devtool build-image costa-embedded-image
```
## Kernel development with devtool
- Refer to [Using devtool to Patch the Kernel](https://docs.yoctoproject.org/kernel-dev/common.html#using-devtool-to-patch-the-kernel)
```shell

# Use the following devtool command to check out the code.
devtool modify linux-yocto

cd build/workspace/sources/linux-yocto
# Edit the init/calibrate.c.
git commit -m "calibrate: Add printk example"
# Add yocto test driver (drivers > misc > yp_driver.c)
git commit -m "Added yocto project driver"
# Enable yp_driver in menuconfig.
devtool menuconfig linux-yocto

# Build the Updated Kernel Source.
devtool build linux-yocto
devtool build-image costa-embedded-image
 
# Test new image.
runqemu nographic
dmesg | less

# Export the Patches and Create an Append File.
devtool finish linux-yocto meta-costa-embedded
bitbake costa-embedded-image

# Alternative Traditional Kernel Development to Patch the Kernel.
git format-patch -1
bitbake linux-yocto -c diffconfig
bitbake linux-yocto -c menuconfig
```

## Kernel recipe
```shell
# Build linux-yocto
bitbake virtual/kernel
bitbake costa-embedded-image

# Append kernel configuration fragment: files://smb.cfg
runqemu nographic
$ cat /proc/cpuinfo | grep processor
processor       : 0


```
