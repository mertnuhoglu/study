import {filter, map, pipe} from 'ramda'
export function isEven (i) {
  return  i % 2 === 0
}
export function square (i) {
  return  i * i
}
export const evenSquares = pipe(
  filter(isEven),
  map(square)
)
