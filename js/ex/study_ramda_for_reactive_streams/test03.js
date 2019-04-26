import test from 'ava'
import {isEven, square} from './utils03'
import snapshot from 'snap-shot'
test('Data-driven isEven', t => {
  snapshot(isEven, 2, 4, 1, 11)
})

