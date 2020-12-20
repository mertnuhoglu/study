--- 
title: "Articles Clojure"
date: 2020-12-20T14:53:28+03:00 
draft: false
description: ""
tags:
categories: clojure
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# REPL driven development with Clojure using Spacemacs, CIDER and clj-kondo - Practicalli id=g_11830

[(112) REPL driven development with Clojure using Spacemacs, CIDER and clj-kondo - YouTube](https://www.youtube.com/watch?v=NDrpclY54E0&list=PLpr9V-R8ZxiCHMl2_dn1Fovcd34Oz45su&index=19)

```bash
clojure -X:project/new :template app :name practicalli/random-function
```

Rich comment:

```clj
_#
(
	...
)
```

![Auto-complete and documentation](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_155117.jpg)

  | h h       | cider-doc                                         |
  | RET       | goto source code in cider-doc                     |

![Flycheck error message](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_162747.jpg)

  | SPC e L   | goto-flycheck-error-list                          |

![goto-flycheck-error-list](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_162831.jpg)

  | e f       | cider-eval-defun-at-point                         |

![cider-eval-defun-at-point](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_163219.jpg)

  | e ;       | cider-eval-defun-to-comment                       |

![cider-eval-defun-to-comment](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_163352.jpg)

Truncated eval result

```clj
(set! *print-length* 10)
```

![*print-length*](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_204343.jpg)

  | , d v l   | cider-inspect-last-result                         |
  | n p       | next/prev page                                    |
  | RET L     | go deep / go back                                 |

![Value inspector > cider-inspect-last-result](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_204431.jpg)

  | SPC k     | lisp                                              |
  | SPC k w   | wrap (with parens)                                |
  | , d v f   | cider-inspect-defun-at-point                      |

Edit window in `window-transient-state`

  | SPC w .   | window-transient-state                            |

![window-transient-state](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_210925.jpg)

Transient state içindeyken `SPC` ile yeni komutlar açılır:

![SPC inside window-transient-state](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_211253.jpg)

Transient state içindeyken `SPC z` ile zoom için ara transient state açılır:

  | SPC z x   | scale-font-transient-state                        |

![scale-font-transient-state](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_211406.jpg)

Toggle/switch between windows:

  | SPC w TAB | alternate-window                                  |

Toggle/switch between source file and its test file:

  | SPC p a   | projectile-toggle-between-implementation-and-test |

Testleri çalıştırmak için, önce test dosyasındaki tüm varları eval ettik:

  | e b       | cider-eval-buffer                                 |

Sonra da tüm testleri run ettik:

  | t a       | cider-test-run-all-tests                          |

p01: Test runları önce çalışmadı. Debug etmek için önce shell üzerinden çalıştırdık:

  | SPC '     | open shell                                        |

```bash
clojure -M:test/kaocha
```

`test/kaocha` varsayılan `~/deps.edn` içinde tanımlı.

Burada test run aldık. Demek ki, cider tarafıyla ilgili bir sorun var.

Sorunun sebebi: `deps.edn` dosyasında `test` dosyaları tanımlı, ancak bunlar `cider-jack-in` yapılırken cidera yüklenmiyor. Bunları eklemek için:

quit repl: `, m q`

Universal argument: `SPC u`

  | , m q     | sesman-quit                                       |
  | SPC u     | universal-argument                                |

Şimdi tekrar cider-jack-in yapınca, komutu edit edebiliriz.

![-M:test](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_232213.jpg)

Bu opsiyonu sürekli olarak eklemek için:

  | SPC p e   | projectile-edit-dir-locals                        |

Edit `/Users/mertnuhoglu/codes/clj/random-function/.dir-locals.el`

```clj
((clojure-mode . ((cider-clojure-cli-global-options . "-M:test"))))
```

Şimdi cider'ı yeniden başlattığında, otomatik bu argümanı set eder.

Check message buffer:

  | SPC b m   | Messages buffer                                   |
	
![](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201219_233743.jpg)

Tekrar proje dosyana dönmek için: `SPC TAB`

  | SPC TAB   | Last buffer                                       |

Editing a function's arguments shows the function signature in mini buffer:

![Function signature in mini buffer](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_111329.jpg)

Hiding some of the stacktraces in error message buffer:

![Hiding stacktraces](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_112714.jpg)

Navigate to source code in stacktrace:

![Navigate to source code](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_112815.jpg)

Pretty print result:

	|e p f| cider-pprint-eval-defun-at-point|

![cider-pprint-eval-defun-at-point](/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20201220_114414.jpg)


