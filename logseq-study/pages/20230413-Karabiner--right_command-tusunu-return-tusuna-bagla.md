tags:: study, macos/karabiner

# 20230413-Karabiner--right_command-tusunu-return-tusuna-bagla id=g14211

Tek başına `right_command` basıldığında -> `return` olsun

```
                    {
                        "description": "right_command to shift+option",
                        "manipulators": [
                            {
                                "from": {
                                    "key_code": "right_command",
                                    "modifiers": {
                                        "optional": [
                                            "any"
                                        ]
                                    }
                                },
                                "to": [
                                    {
                                        "key_code": "left_option",
                                        "modifiers": [
                                            "left_shift"
                                        ]
                                    }
                                ],
                                "to_if_alone": [
                                    {
                                        "key_code": "return_or_enter"
                                    }
                                ],
                                "type": "basic"
                            }
                        ]
                    },
```

Diğer return tuşlarını iptal et. Böylece kas hafızan eski alışkanlıklarını unutsun.

```
                    {
                        "description": "alt+shift+space to alt+shift+tab to return",
                        "manipulators": [
                            {
                                "from": {
                                    "key_code": "spacebar",
                                    "modifiers": {
                                        "mandatory": [
                                            "left_shift",
                                            "left_option"
                                        ],
                                        "optional": [
                                            "caps_lock"
                                        ]
                                    }
                                },
                                "to": [
                                    {
                                        "key_code": "return_or_enter"
                                    }
                                ],
                                "type": "basic"
                            }
                        ]
                    },
                    {
                        "description": "command+space to return",
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
                                "to_if_alone": [
                                    {
                                        "key_code": "return_or_enter"
                                    }
                                ],
                                "type": "basic"
                            }
                        ]
                    },
```

Sorun: Bu durumda `right_command` tuşuna bastıktan sonra, yapacağın işlemden vazgeçersen, yine `return` işlemi olacak. Halbuki ben birçok zaman vazgeçiyorum, ikinci tuşa basmaktan.
