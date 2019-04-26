import test from 'ava'
import {isEven, square, evenSquares} from './utils05'
import snapshot from 'snap-shot'
test('even squares', t => {
  snapshot(evenSquares([0, 1, 2, 3]))
})

