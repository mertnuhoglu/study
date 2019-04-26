import test from 'ava'
import {isEven, square} from './utils02'
test('isEven', t => {
  t.true(isEven(2))
  t.true(isEven(4))
  t.false(isEven(1))
  t.false(isEven(11))
})
test('isEven snapshot', t => {
  t.snapshot([2, 4, 1, 11].map(isEven))
})

