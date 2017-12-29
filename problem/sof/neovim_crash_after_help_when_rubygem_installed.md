
https://stackoverflow.com/questions/48002815/neovim-crashes-after-help-command-when-rubygem-is-installed

## Neovim crashes after :help command when RubyGem is installed

I have just recently installed `nvim 0.2.2` on OsX El Capitan v10.11.6 using the command: `brew install neovim`

    $ brew info neovim
    neovim: stable 0.2.2 (bottled), HEAD
    Ambitious Vim-fork focused on extensibility and agility
    https://neovim.io/
    /usr/local/Cellar/neovim/0.2.2 (1,374 files, 17.6MB) *
      Poured from bottle on 2017-12-27 at 22:45:13
    From: https://github.com/Homebrew/homebrew-core/blob/master/Formula/neovim.rb
    ==> Dependencies
    Build: cmake ✘, lua@5.1 ✘, pkg-config ✔
    Required: gettext ✔, jemalloc ✔, libtermkey ✔, libuv ✔, libvterm ✔, luajit ✔, msgpack ✔, unibilium ✔

    $ nvim --version
    NVIM v0.2.2
    Build type: Release
    LuaJIT 2.0.5
    Compilation: /usr/local/Homebrew/Library/Homebrew/shims/super/clang -Wconversion -U_FORTIFY_SOURCE -D_FORTIFY_SOURCE=1 -DNVIM_MSGPACK_HAS_FLOAT32 -DNVIM_UNIBI_HAS_VAR_FROM -DNDEBUG -DMIN_LOG_LEVEL=3 -Wall -Wextra -pedantic -Wno-unused-parameter -Wstrict-prototypes -std=gnu99 -Wimplicit-fallthrough -Wvla -fstack-protector-strong -fdiagnostics-color=auto -DINCLUDE_GENERATED_DECLARATIONS -I/tmp/neovim-20171118-36724-uevp4/neovim-0.2.2/build/config -I/tmp/neovim-20171118-36724-uevp4/neovim-0.2.2/src -I/usr/local/include -I/usr/local/include -I/usr/local/include -I/usr/local/include -I/usr/local/include -I/usr/local/include -I/usr/local/opt/gettext/include -I/usr/include -I/tmp/neovim-20171118-36724-uevp4/neovim-0.2.2/build/src/nvim/auto -I/tmp/neovim-20171118-36724-uevp4/neovim-0.2.2/build/include
    Compiled by brew@ElCapitan-2.local

    Features: +acl +iconv +jemalloc +tui 
    See ":help feature-compile"

       system vimrc file: "$VIM/sysinit.vim"
      fall-back for $VIM: "/usr/local/Cellar/neovim/0.2.2/share/nvim"

    Run :checkhealth for more info

`nvim` runs properly. But after installing `neovim` RubyGem, I cannot use `:help` command in `neovim`. The application crashes immediately:

    $ gem install neovim
    Fetching: neovim-0.6.2.gem (100%)
    Successfully installed neovim-0.6.2
    1 gem installed

Executing `:help` command in `nvim` gives the following error message:

    nvim(69661,0x7fff7274e000) malloc: *** error for object 0x7f9414310d00: pointer being freed was not allocated
       *** set a breakpoint in malloc_error_break to debug
                                                      [1]    69661 abort      nvim

I use `rvm 1.26.11` to manage ruby. 

    $ rvm list

    rvm rubies

    =* ruby-2.1.1 [ x86_64 ]

    $ rvm --version
    rvm 1.26.11 (latest) by Wayne E. Seguin <wayneeseguin@gmail.com>, Michal Papis <mpapis@gmail.com> [https://rvm.io/]

    $ rvm gemdir
    /Users/mertnuhoglu/.rvm/gems/ruby-2.1.1

    $ gem --version
    2.4.8


