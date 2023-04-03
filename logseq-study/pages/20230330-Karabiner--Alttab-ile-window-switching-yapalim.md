
# 20230330-Karabiner--Alttab-ile-window-switching-yapalim id=g14167

Alttab ile aynı uygulamanın pencereleri arasında geçiş yapmak mümkün. 

Daha önceden ben `Tab` tuşunu karabiner ile bu amaçla kullanıyordum.

Şimdi Tab tuşunu eski haline getirelim: `~/prj/private_dotfiles/.config/karabiner/karabiner.json`

```json
                    {
                        "description": "normal command+tab to cmd+option+3 which is mapped to swich windows",
                        "manipulators": [
                            {
                                "from": {
                                    "key_code": "tab",
                                    "modifiers": {
                                        "mandatory": [
                                            "left_command"
                                        ]
                                    }
                                },
                                "to": [
                                    {
                                        "key_code": "3",
                                        "modifiers": [
                                            "command",
                                            "left_option",
                                            "left_control"
                                        ]
                                    }
                                ],
                                "type": "basic"
                            }
                        ]
                    },
```

```json
                    {
                        "description": "Dual keys: change tab to control if alone, to itself with other keys.",
                        "manipulators": [
                            {
                                "from": {
                                    "key_code": "tab",
                                    "modifiers": {
                                        "optional": [
                                            "any"
                                        ]
                                    }
                                },
                                "parameters": {
                                    "basic.to_if_alone_timeout_milliseconds": 300,
                                    "basic.to_if_held_down_threshold_milliseconds": 0
                                },
                                "to_if_held_down": [
                                    {
                                        "key_code": "left_control"
                                    }
                                ],
                                "type": "basic"
                            }
                        ]
                    },
```

## Tab tuşunu F13 yap

Tab tuşunu F13'e eşleştirelim. Sonra Alttab'da ekran değiştirmeyi: Cmd+F13 olarak ayarlayalım.

```
                    {
                        "description": "Change tab to F13",
                        "manipulators": [
                            {
                                "from": {
                                    "key_code": "tab",
                                    "modifiers": {
                                        "optional": [
                                            "any"
                                        ]
                                    }
                                },
                                "to": [
                                    {
                                        "key_code": "f13"
                                    }
                                ],
                                "type": "basic"
                            }
                        ]
                    },
```

## Cmd+Space artık Cmd+Tab olmasın

```
                    {
                        "description": "option+space to command+tab",
                        "manipulators": [
                            {
                                "from": {
                                    "key_code": "spacebar",
                                    "modifiers": {
                                        "mandatory": [
                                            "left_command"
                                        ],
                                        "optional": [
                                            "caps_lock"
                                        ]
                                    }
                                },
                                "to": [
                                    {
                                        "key_code": "tab",
                                        "modifiers": [
                                            "command"
                                        ]
                                    }
                                ],
                                "type": "basic"
                            }
                        ]
                    },
```

## Cmd+CapsLock -> Cmd+F14 olsun

```
                    {
                        "description": "command+capslock to command+f14",
                        "manipulators": [
                            {
                                "from": {
                                    "key_code": "right_option",
                                    "modifiers": {
                                        "mandatory": [
                                            "left_command"
                                        ],
                                    }
                                },
                                "to": [
                                    {
                                        "key_code": "F14",
                                        "modifiers": [
                                            "command"
                                        ]
                                    }
                                ],
                                "type": "basic"
                            }
                        ]
                    },
```

AltTab ayarları:

	Cmd+F14 yani Option+Tab -> Switch Apps
	Shift+F14 yani Cmd+Tab -> Switch Windows

## Google Araması

