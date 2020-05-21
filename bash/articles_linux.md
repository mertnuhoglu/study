    <url:file:///~/Dropbox/mynotes/content/articles/articles_linux.md>

## Processes

    The ps Command
      http://www.linfo.org/ps.html
      pid: process identification number
      process: task, is an executing instance of a program
      syntax: ps [options]
      ps without options
        at least two processes:
          shell, ps
          shell: bash
        4 items of information 
          pid
          tty: terminal type
            name of console that user logged into
          time: amount of cpu time in mins
          cmd: name of the command
      $ ps -aux | less
        -a  list processes of all users
        -u  detailed information
        -x  include daemons
        information:
          user, pid, % CPU, % memory, VSZ (virtual size in kb), RSS (real memory size = resident set size in kb), STAT (process state code), start time, length of time, command
          state codes: D N R S T Z
      $ ps -ef | less
        -e  every process currently running
        -f  less info than -l
        information:
          uid (username), STIME
    Example Uses Of The Linux ps Command
      https://www.lifewire.com/uses-of-linux-ps-command-4058715
      all running processes
        $ ps -A
        $ ps -e
      $ ps T
        processes in this terminal
      $ ps r
        all running processes
      selecting specific processes
        $ ps -p <pid>
        $ ps -C <command>
          $ ps -C chrome
        by group
          $ ps -G <group>
          $ ps --Group <group>
          $ ps -g <groupid>
        $ ps -s <sessionid>
        $ ps -U <userlist>
      sorting
        ps -ef --sort <columns>


## File Permissions

    http://ryanstutorials.net/linuxtutorial/permissions.php
      what are they?
        3 things you can do with a file:
          r read
          w write
          x execute
        3 sets of people:
          owner
          group: a file belongs to a single group
          others
      view permissions
        ls -l
          -rwxr----x ...
          1: file type
            "-" normal file
            d   directory
          2-4: permissions for owner
            -   absence of permission
            rwx: has all permissions
          5-6: permissions for group
          7-9: permissions for others
      change permissions
        chmod: change file mode bits
          bit: permission indicators
        chmod [permissions] [path]
        arguments: 3 components
          whose permission: 
            [ugoa]: user(owner), group, others, all
          grant/revoke permission 
            + or -
          which permission
            r or w or x
        ex
          $ ls -l frog.png
          -rwxr----x 1 harry users 2.7K Jan 4 07:32 frog.png
          $ chmod g+x frog.png
          $ ls -l frog.png
          -rwxr-x--x 1 harry users 2.7K Jan 4 07:32 frog.png
          $ chmod u-w frog.png
          $ ls -l frog.png
          -r-xr-x--x 1 harry users 2.7K Jan 4 07:32 frog.png
        ex: multiple permissions at once
          chmod g+wx frog.png
          chmod go-x frog.png
      permissions shorthand
        logic
          3 permissions
          each can be on or off
          8 possible combinations: 2^3
          map between octal and binary
            5 = 101
            means: 
              1: on read
              0: off write
              1: on execute
          751 means:
            7 for owner
            5 for group
            1 for others
      permissions for directories
        r read
        w write
        x enter (cd)
      root user
        2 users can change permissions
          root, owner
      basic security
    Add a User to a Group (or Second Group) on Linux
      https://www.howtogeek.com/50787/add-a-user-to-a-group-or-second-group-on-linux/
      $ groups
        list groups 
      create a new group
        $ groupadd <name>
      add an existing user to a group
        $ usermod -a -G <group> <username>
        ex: add user geek to group sudo
          $ usermod -a -G sudo geek
      change a user's primary group
        a user can have multiple groups. 
          but one group is primary and others are secondary
        user creates files and folders
          they are assigned to primary group
        change primary group
          $ usermod -g <group> <user>
        assign secnodary group
          $ usermod -G <group> <user>
      view the groups of a user
        for current user
          $ groups
          uid=1000(chris) gid=1000(chris) groups=1000(chris),4(adm)...
          # gid=1000(chris) is primary group
        view numerical ids of each group
          $ id
        $ groups <user>
        $ id <user>
      create a user and assign a group
        $ useradd -G <group> <username>
        assign a password afterwards
          $ passwd <username>
      add a user to multiple groups
        $ usermod -a -G group1,group2 <username>
      view all groups on system
        $ getent group
    https://www.pluralsight.com/blog/it-ops/linux-file-permissions
      chgrp: changing groups of files
      chown: change ownership
    https://www.digitalocean.com/community/tutorials/linux-permissions-basics-and-how-to-use-umask-on-a-vps#types-of-permissions
      octal notation
        4: read
        2: write
        1: execute
        sum them up: 6 = 4 + 2
      umask: setting default permissions
        files have 666: full rw for all
        directories have: 777
        umask: applies subtractive mask
        ex: to make 775 default for directories
          umask 002
        ex: to make 666 and 777 default
          umask 000
        put umask into .bashrc to persist across sessions
    https://www.linode.com/docs/tools-reference/linux-users-and-groups
      changing file ownership
        chown cjones:marketing list.html
          change ownership to cjones user in marketing group
        chown -R ...
          recursively
    Howto: Linux Add User To Group
      https://www.cyberciti.biz/faq/howto-linux-add-user-to-group/
      2 types of groups:
        primary user group
        secondary user group
      user account information stored in:
        /etc/passwd
          one line per user
        /etc/shadow
          passwords encrypted
        /etc/group
          defines groups
        /etc/default/useradd
          value for default group
        /etc/login.defs
          configuration for shadow password
      useradd: add a new user to secondary group
        ex: create user "vivek" add it to group "developers"
          make sure group "developers" exist
            $ grep developers /etc/group
            developers:x:1124:
          add group "developers" if none exists
            $ groupadd developers
          make sure user "vivek" does not exist
            $ grep ^vivek /etc/passwd
          add user "vivek"
            $ useradd -G developers vivek
          setup password for "vivek"
            $ passwd vivek
          ensure "vivek" is added to group
            $ id vivek
            uid=1122(vivek) gid=1125(vivek) groups=1125(vivek),1124(developers)
      usermod: add an existing user to existing group
    primary group vs secondary group
      What is the difference between primary group and secondary group in Ubuntu?
        https://askubuntu.com/questions/538130/what-is-the-difference-between-primary-group-and-secondary-group-in-ubuntu
        ex: you have primary group "x" secondary "y"
          $ touch foo
            foo: x group owner
          $ sg y 'touch bar'
            bar: y group owner
        ex: you don't have secondary group
          $ sg y 'touch bar'
            asks for group password
        check secondary groups 
          groups $(whoami)
    Shared Folder with chmod SetGID
      http://terokarvinen.com/2011/shared-folder-with-chmod-setgid
      how to share a folder?
      how to get write permission to files other users have created?
      problem: don't make folders writable to others
        even daemons can write then
      solution: set group id
        code
          $ mkdir foo
          $ chmod g+s foo/ 
            set group id
          $ sudo chown .botbook foo
        we set SetGID aka set group id bit using chmod
        SetGID means: all files under foo/ will
          have same owner group "botbook"
          will have SetGID set
        check
          $ ls -l
          drwxrws--- 2 tero botbook 4096 2011-04-12 10:37 foo
        note: "s" in group permissions in place of "x"
          that is SetGID bit
        testing
          $ touch foo/bar
          $ ls -l foo/bar
          -rw-rw---- 1 tee botbook 0 2011-04-12 10:39 foo/bar
          note: group "botbook"
    Switch between users
      https://unix.stackexchange.com/questions/3568/how-to-switch-between-users-on-one-terminal
      su command
        $ whoami
        user1
        $ su - user2
        Password: 
        $ whoami
        user2
        $ exit
        logout
      to switch to root, no need for username
        $ su -
        $ whoami
        root
      sudo: to launch a new shell as some user
        $ sudo -u user2 zsh
        $ whoami
        user2
    Linux: Add User to Group (Primary/Secondary/New/Existing)
      http://www.hostingadvice.com/how-to/linux-add-user-to-group/
      creating a user 
        sudo adduser <username> 
      delete a group
        sudo groupdel <group>
      user administration
        newgrp: log into a new group
        sg: execute a command as a different group ID
        groupmod: modify a group definition (e.g., the group ID, group name, or password)
        gpasswd: administer /etc/group and /etc/gshadow files (every group can have administrators, members, and a password)
        chown or chgrp: change individual or group ownership of a file or directory
    How to Change File Ownership & Groups in Linux
      http://www.hostingadvice.com/how-to/change-file-ownershipgroups-linux/
      who owns the file: ls -l
        $ ls -l <file>
        ... root www-data
          owned by root user
          belongos to www-data group
      change ownership of file: chown
        $ chown robert <file>
        $ ls -l <file>
        ... robert www-data
        recursively:
          chown -R <user> <dir>/
      change group of a file: chgrp
        $ chgrp <group> <file>
        $ ls -l <file>
        ... robert <group>
      change owner and group: chown
        $ chown <user>:<group> <file>
    adduser vs useradd
      https://askubuntu.com/questions/345974/what-is-the-difference-between-adduser-and-useradd
        useradd: native binary compiled
        adduser: perl script that uses useradd
          more user friendly
        main differences
          adduser: creates home directory and skeleton files
    Understanding /etc/group File
      https://www.cyberciti.biz/faq/understanding-etcgroup-file/
      /etc/group file
        ex: one entry per line
          cdrom:x:24:vivek,raj
            1:2:3:4
          1  group_name
            "ls -l" shows this name in group field
          2  password
          3  group id (gid)
            gid of a user is in /etc/passwd
          4  group list
            list of user names
        find out groups a user is in
          groups <username>
          groups
    fields in ls -l
      https://stackoverflow.com/questions/28991481/what-do-the-fields-in-ls-ali-output-mean
      ls -ali
        +--------------+------------------+-----------------+-------+-------+------+-------+-----+-------+-----------+
        | index number | file permissions | number of links | owner | group | size | month | day | time  | filename  |
        +--------------+------------------+-----------------+-------+-------+------+-------+-----+-------+-----------+
        |       933442 | -rwxrw-r--       |              10 | root  | root  | 2048 | Jan   |  13 | 07:11 | afile.exe |
        +--------------+------------------+-----------------+-------+-------+------+-------+-----+-------+-----------+
    sudo su vs sudo -i vs sudo /bin/bash
      https://askubuntu.com/questions/376199/sudo-su-vs-sudo-i-vs-sudo-bin-bash-when-does-it-matter-which-is-used
        su: switch user
          su -
            switch to root
        sudo: run a single command with root
          prompts password of current user
          user must be in sudoers file or group 
        bash: interface to interact with computer
          login shell: 
          non-login shell: graphic terminal in gnome
          interactive shell: interactively type commands
          non-interactive shell: to run automated process
        sudo su: calls sudo with command su
          bash is called as interactive non-login shell
            so bash executes .bashrc
          you are still in the same directory
        sudo su -: this time it is login shell
          so /etc/profile, .profile, .bashrc are executed
          you are in root's home
        sudo /bin/bash
      https://askubuntu.com/questions/70534/what-are-the-differences-between-su-sudo-s-sudo-i-sudo-su
        sudo su:
          first: sudo asks you for your password
          next: invokes "su" as a super-user
            since su is invoked by root, it doesn't require to enter target user's password
        sudo -s: runs a shell with root privileges
          in your directory
          ~/.bashrc is respected
        sudo -i: same but in root environment
          ~/.bashrc is ignored
          better than -s
      https://www.howtogeek.com/111479/htg-explains-whats-the-difference-between-sudo-su/
        sudo: asks your password
        su -: asks root password
    login shell vs non-login shell
      https://unix.stackexchange.com/questions/38175/difference-between-login-shell-and-non-login-shell
      login shell: first process that runs under your user id when you log in
    username 500 1000
      ex
        root@c7bb08d325c3:/srv/app# ls -l
        drwxrwxr-x 3 500 500 4096 Aug  3 16:54 data
        root@c7bb08d325c3:/srv# id opencpu
        uid=1000(opencpu) gid=33(www-data) groups=33(www-data)
      500 and 1000 are first non-root user
    How To View System Users in Linux on Ubuntu
      https://www.digitalocean.com/community/tutorials/how-to-view-system-users-in-linux-on-ubuntu
      list users
        $ less /etc/passwd
          root:x:0:0:root:/root:/bin/bash
          daemon:x:1:1:daemon:/usr/sbin:/bin/sh
        $ cut -d : -f 1 /etc/passwd
          root 
          daemon
      list groups
        $ less /etc/group
      list users logged in
        $ w
        $ who



## Sockets

  unix sockets - tutorialspoint.com
    what is a socket
      https://www.tutorialspoint.com/unix_sockets/what_is_socket.htm
      socket
        allow communication between two different processes
        uses unix file descriptors
          an integer associated with open file 
          read() write() work like they do with files and pipes
      socket types
        4 types
        1. stream sockets
          use tcp
        2. datagram sockets
          not guaranteed in network
          don't have an open connection
          use udp
        3. raw sockets
          not for general user
        4. sequenced packet sockets
    network addresses
    network host names
      hostname resolution
        host name -> ip address
        done by DNS
        done by /etc/hosts
