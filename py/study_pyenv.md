---
title: "Study pyenv"
date: 2020-07-11T10:05:10+03:00 
draft: true
description: ""
tags:
categories: python
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
path: ~/projects/study/py/study_pyenv.Rmd
state: wip

---

# FINAL

pyenv kurulumu için çok uğraştım. 

Şu hatayı bir türlü düzeltemedim: `Error: ModuleNotFoundError: No module named 'pyexpat' <url:file:///~/projects/study/py/study_pyenv.md#r=g11566>`

Son kararım: `pyenv` kullanma. Eski usul brew ile yönet. Upgrade edeceğin vakit, eski usul manuel ilerle.

# Issues

## install latest python

[Managing Multiple Python Versions With pyenv – Real Python](https://realpython.com/intro-to-pyenv/#using-pyenv-to-install-python)

```clojure
pyenv install --list | rg " 3\."
  ##>   3.8.1
  ##>   3.8.2
  ##>   3.8.3
  ##>   3.8.4rc1
pyenv install 3.8.3
```

### Error: ModuleNotFoundError: No module named 'pyexpat' id=g11566

Çözüm: `symlink to brew python  <url:file:///~/projects/study/py/study_pyenv.md#r=g11478>`

## making brew compatible with pyenv

[Troubleshooting the Pyenv/Homebrew Combination - Blog - Amaral Lab](https://amaral.northwestern.edu/blog/troubleshooting-pyenv)

(1) Homebrew is for installing and managing general system tools that are useful for many programs outside of python. 

(2) Use pyenv with pip ONLY for installing and managing python.

Rule 1: Only brew install while pyenv global is set to system and never brew while in a virtual environment.

```clojure
~$ brew uninstall that_thing_you_installed_wrong
~$ pyenv global system
~$ brew install that_thing_you_installed_wrong
```

Rule 2: The pyenv directory must be before “/usr/local/bin” in your PATH

Bu sırada olmalı:

```clojure
export PATH=/usr/local/bin:$PATH
export PYENV_ROOT="$HOME/.pyenv"
export PATH="$PYENV_ROOT/bin:$PATH"
```

### symlink to brew python  id=g11478

[How can I make homebrew's python and pyenv live together? - Stack Overflow](https://stackoverflow.com/questions/30499795/how-can-i-make-homebrews-python-and-pyenv-live-together)

create a symlink from pyenv to brew

```clojure
ln -s $(brew --cellar python)/* ~/.pyenv/versions/
```

@mine: bu yöntem brew'e bırakıyor python yönetimini. halbuki pyenv yönetse daha kolay olur. ama `pyenv install` sürekli hata veriyor.

# README.md

[README](https://github.com/pyenv/pyenv)

How it works?

- Search your PATH for an executable file named pip
- Find the pyenv shim named pip at the beginning of your PATH
- Run the shim named pip, which in turn passes the command along to pyenv

## Setup

```bash
git clone https://github.com/pyenv/pyenv.git ~/.pyenv
echo 'export PYENV_ROOT="$HOME/.pyenv"' >> ~/.zshrc
echo 'export PATH="$PYENV_ROOT/bin:$PATH"' >> ~/.zshrc
echo -e 'if command -v pyenv 1>/dev/null 2>&1; then\n  eval "$(pyenv init -)"\nfi' >> ~/.zshrc
exec "$SHELL"
```

Python dependencies

```bash
brew upgrade openssl readline sqlite3 xz zlib
```

Verify installation:

```bash
which pyenv
echo $PATH | grep --color=auto "$(pyenv root)/shims"
```

```bash
cd $(pyenv root)
git pull
```

you will accumulate Python versions in your `$(pyenv root)/versions` directory

```clojure
pyenv install 2.7.8
pyenv uninstall 2.7.8
pyenv prefix 2.7.8
```

# Article: PyEnv is the new Conda

[PyEnv is the new Conda](https://bastibe.de/2017-11-20-pyenv.html)

conda cons:

- package and environment manager
- broke pip once
- gets code from anaconda servers

pyenv pros:

- you install a python
- use pythons own venv and pip

much simpler. orthogonal tools. 

## article: The right and wrong way to set up Python 3 on MacOS  id=g11469

[The right and wrong way to set up Python 3 on MacOS | Opensource.com](https://opensource.com/article/19/5/python-3-default-mac)

``` 
brew list | rg python
  ##> python
  ##> python3
  ##> python@2
  ##> python@3.8
ls /usr/local/bin/ | rg python
``` 

best solution: `pyenv`

## article: How to set up virtual environments for Python on MacOS  id=g11470

[How to set up virtual environments for Python on MacOS | Opensource.com](https://opensource.com/article/19/6/python-virtual-environments-mac)

``` 
brew install pyenv
export LDFLAGS="-L/usr/local/opt/zlib/lib -L/usr/local/opt/sqlite/lib $LDFLAGS"
export CPPFLAGS="-I/usr/local/opt/zlib/include -I/usr/local/opt/sqlite/include $CPPFLAGS"
brew install zlib sqlite
``` 

``` 
pyenv install 3.7.3
``` 

## article: Installing Python packages in 2019: pyenv and pipenv

[Installing Python packages in 2019 pyenv and pipenv](https://gioele.io/pyenv-pipenv)

[Managing Multiple Python Versions With pyenv – Real Python](https://realpython.com/intro-to-pyenv/)

## Setting up python environments 

@mine: conda kullanma. pyenv kullan. daha kullanışlı.

``` bash
conda search "^python$"
conda create --name py3 python=3
``` 

``` bash
conda activate py3
python --version
  ##> Python 3.7.2
conda deactivate 
python --version
  ##> Python 3.7.1
exit
python --version
  ##> Python 2.7.12
conda info --envs
  ##> # conda environments:
  ##> #
  ##> base                  *  /home/ubuntu/anaconda3
  ##> py3                      /home/ubuntu/anaconda3/envs/py3

``` 

Install new package into an environment

``` bash
conda install --name py3 numpy
``` 

Remove environment

``` bash
conda remove --name py3 --all
``` 

## Updating conda

``` bash
conda update conda
conda update anaconda
``` 

Uninstall

``` bash
conda install anaconda-clean
anaconda-clean
rm -rf anaconda3
``` 

# pyenv


