~function(global) {
  const Parcel = {}
  Parcel.baseRequire = typeof require !== "undefined" ? require : n => {
    throw new Error(`Could not resolve module name: ${n}`)
  }
  Parcel.modules = {}
  Parcel.files = {}
  Parcel.mains = {}
  Parcel.resolve = (base, then) => {
    base = base.split('/')
    base.shift()
    for (const p of then.split('/')) {
      if (p === '..') base.pop()
      else if (p !== '.') base.push(p)
    }
    return '/' + base.join('/')
  }
  Parcel.Module = function Module(filename, parent) {
    this.filename = filename
    this.id = filename
    this.loaded = false
    this.parent = parent
    this.children = []
    this.exports = {}
  }
  Parcel.makeRequire = self => {
    // let parts

    const require = m => require._module(m).exports
    require._deps = {}
    require.main = self

    require._esModule = m => {
      const mod = require._module(m)
      return mod.exports.__esModule ? mod.exports : {
        get default() {return mod.exports},
      }
    }
    require._module = m => {
      let fn = self ? require._deps[m] : Parcel.main
      // if (fn === undefined) {
      //   const filename = require.resolve(m)
      //   fn = filename !== null ? Parcel.files[filename] : null
      // }
      if (fn == null) {
        const module = {exports: Parcel.baseRequire(m)}
        require._deps[m] = {module: module}
        return module
      }
      if (fn.module) return fn.module
      const module = new Parcel.Module(fn.filename, self)
      fn.module = module
      module.require = Parcel.makeRequire(module)
      module.require._deps = fn.deps
      module.require.main = self ? self.require.main : module
      if (self) self.children.push(module)
      fn(module, module.exports, module.require)
      module.loaded = true
      return module
    }
    // require.resolve = n => {
    //   if (!self) return n
    //   if (n[0] === '.' || n[0] === '/') {
    //     const p = resolvePath(n[0] === '.' ? Parcel.resolve(self.filename, '../'+n) : n)
    //     if (p) return p
    //   } else {
    //     if (!parts) {
    //       parts = self ? self.filename.split('/') : []
    //       parts.shift()
    //     }
    //     const p = parts.slice()
    //     while (p.length) {
    //       p.pop()
    //       if (p[p.length - 1] === 'node_modules') continue
    //       const r = resolvePath('/' + p.join('/') + '/node_modules/' + n)
    //       if (r) return r
    //     }
    //   }
    //   return null
    // }
    // const resolvePath = b => {
    //   const m = Parcel.mains[b]
    //   if (m) return m
    //   if (Parcel.files[b+'/index.js']) return b+'/index.js'
    //   if (Parcel.files[b+'/index.json']) return b+'/index.json'
    //   if (Parcel.files[b]) return b
    //   if (Parcel.files[b+'.js']) return b+'.js'
    //   if (Parcel.files[b+'.json']) return b+'.json'
    // }
    return require
  }

  Parcel.files["/Users/mertnuhoglu/projects/study/js/ex/study_parcel_redux/ex02/index.js"] = file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2findex$2ejs; file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2findex$2ejs.deps = {"./math":file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2fmath$2ejs,"itt":file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2fnode_modules$2fitt$2findex$2ejs}; file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2findex$2ejs.filename = "/Users/mertnuhoglu/projects/study/js/ex/study_parcel_redux/ex02/index.js"; function file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2findex$2ejs(module, exports, require) {
// index.js:
const itt = require('itt')
const math = require('./math')
console.log(itt.range(10).map(math.square).join(' '))

}
  Parcel.files["/Users/mertnuhoglu/projects/study/js/ex/study_parcel_redux/ex02/math.js"] = file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2fmath$2ejs; file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2fmath$2ejs.deps = {}; file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2fmath$2ejs.filename = "/Users/mertnuhoglu/projects/study/js/ex/study_parcel_redux/ex02/math.js"; function file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2fmath$2ejs(module, exports, require) {
exports.square = x => x * x
}
  Parcel.files["/Users/mertnuhoglu/projects/study/js/ex/study_parcel_redux/ex02/node_modules/itt/index.js"] = file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2fnode_modules$2fitt$2findex$2ejs; file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2fnode_modules$2fitt$2findex$2ejs.deps = {}; file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2fnode_modules$2fitt$2findex$2ejs.filename = "/Users/mertnuhoglu/projects/study/js/ex/study_parcel_redux/ex02/node_modules/itt/index.js"; function file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2fnode_modules$2fitt$2findex$2ejs(module, exports, require) {
'use strict'

function is(xs) {return typeof xs[Symbol.iterator] === 'function' || typeof xs.next === 'function'}
function generator(gen) {return (...args) => new Iter(gen(...args))}
const G = generator
function from(iter) {return new Iter(iter[Symbol.iterator] ? iter[Symbol.iterator]() : iter)}
const empty = G(function*() {})

const range = G(function*(start, end, skip = 1) {
  if (end === undefined) {end = start; start = 0}
  if (skip > 0) for (let i = start; i < end; i += skip) yield i
  else for (let i = start; i > end; i += skip) yield i
})
const irange = G(function*(start = 0, skip = 1) {
  for (let i = start; ; i += skip) yield i
})
const replicate = G(function*(n, x) {for (let i = 0; i < n; ++i) yield x})
const forever = G(function*(x) {for (;;) yield x})
const iterate = G(function*(x, fn) {for (;;) {yield x; x = fn(x)}})

const _keys = Object.keys
const entries = G(function*(o) {for (const k of _keys(o)) yield [k, o[k]]})
function keys(o) {return new Iter(_keys(o)[Symbol.iterator]())}
const values = G(function*(o) {for (const k of _keys(o)) yield o[k]})

function fork(n = 2, xs) {
  if (xs === undefined) {xs = n; n = 2}
  return new ForkSource(n, xs).derived
}
const cycle = G(function*(xs) {
  const cache = []
  for (const x of xs) {
    cache.push(x)
    yield x
  }
  for (;;) yield* cache
})
const repeat = G(function*(n, xs) {
  if (n <= 0) return
  const cache = []
  for (const x of xs) {
    cache.push(x)
    yield x
  }
  for (let i = 1; i < n; ++i) yield* cache
})
const enumerate = G(function*(xs) {let i = 0; for (const x of xs) yield [i++, x]})
const map = G(function*(fn, xs) {for (const x of xs) yield fn(x)})
const flatMap = G(function*(fn, xs) {for (const x of xs) yield* fn(x)})
const tap = G(function*(fn, xs) {for (const x of xs) {fn(x); yield x}})
const filter = G(function*(fn, xs) {for (const x of xs) if (fn(x)) yield x})
const reject = G(function*(fn, xs) {for (const x of xs) if (!fn(x)) yield x})
const concat = G(function*(...xss) {for (const xs of xss) yield* xs})
const push = G(function*(...ys) {const xs = ys.pop(); yield* xs; yield* ys})
const unshift = G(function*(...ys) {const xs = ys.pop(); yield* ys; yield* xs})
const flatten = G(function*(xs) {for (const x of xs) yield* x})
const chunksOf = G(function*(n = 2, xs) {
  if (xs === undefined) {xs = n; n = 2}
  if (n <= 0) return
  let list = []
  for (const x of xs) {
    list.push(x)
    if (list.length >= n) {yield list; list = []}
  }
  if (list.length) yield list
})
const subsequences = G(function*(n = 2, xs) {
  if (xs === undefined) {xs = n; n = 2}
  if (n <= 0) return
  if (xs[Symbol.iterator]) xs = xs[Symbol.iterator]()
  let buffer = []
  let value, done
  while (buffer.length < n && ({value, done} = xs.next(), !done)) {
    buffer.push(value)
  }
  if (!done) for (;;) {
    yield buffer
    ;({value, done} = xs.next())
    if (done) return
    buffer = buffer.slice(1)
    buffer.push(value)
  }
})
const lookahead = G(function*(n = 1, xs) {
  if (xs === undefined) {xs = n; n = 1}
  if (xs[Symbol.iterator]) xs = xs[Symbol.iterator]()
  let buffer = []
  let value, done
  while (buffer.length < n && ({value, done} = xs.next()) && !done) {
    buffer.push(value)
  }
  if (!done) while (({value, done} = xs.next()) && !done) {
    buffer.push(value)
    yield buffer
    buffer = buffer.slice(1)
  }
  for (let i = buffer.length - 1; i-- >= 0;) {
    yield buffer
    buffer = buffer.slice(1)
  }
})
const drop = G(function*(n, xs) {for (const x of xs) if (n <= 0) yield x; else --n})
const dropWhile = G(function*(fn, xs) {let init = true; for (const x of xs) if (!init || !fn(x)) {init = false; yield x}})
const dropLast = G(function*(n, xs) {
  if (n <= 0) yield* xs; else {
    const list = []
    let i = 0
    for (const x of xs) {
      if (i >= n) yield list[i % n]
      list[i++ % n] = x
    }
  }
})
const take = G(function*(n, xs) {if (n <= 0) return; for (const x of xs) {yield x; if (--n <= 0) return}})
const takeWhile = G(function*(fn, xs) {for (const x of xs) if (fn(x)) yield x; else return})
const takeLast = G(function*(n, xs) {
  const list = []
  let i = 0
  for (const x of xs) list[i++ % n] = x
  if (n > list.length) n = list.length
  for (let j = 0; j < n; j++) yield list[(i + j) % n]
})
const transpose = G(function*(xss) {
  const its = Array.from(xss, xs => xs[Symbol.iterator]())
  if (!its.length) return
  for (;;) {
    const rs = its.map(it => it.next())
    if (rs.some(r => r.done)) return
    yield rs.map(r => r.value)
  }
})
function zip(...xss) {return transpose(xss)}
const parallel = G(function*(...xss) {
  const its = xss.map(xs => xs[Symbol.iterator]())
  for (;;) {
    const rs = its.map(it => it.next())
    if (rs.every(r => r.done)) return
    yield rs.map(r => r.value)
  }
})

function every(fn, xs) {for (const x of xs) if (!fn(x)) return false; return true}
function some(fn, xs) {for (const x of xs) if (fn(x)) return true; return false}
function find(fn, xs) {for (const x of xs) if (fn(x)) return x}
function findLast(fn, xs) {let y; for (const x of xs) if (fn(x)) y = x; return y}
function findIndex(fn, xs) {let i = 0; for (const x of xs) {if (fn(x)) return i; ++i} return -1}
function findLastIndex(fn, xs) {let i = 0, j = -1; for (const x of xs) {if (fn(x)) j = i; ++i} return j}
function indexOf(y, xs) {let i = 0; for (const x of xs) {if (x === y) return i; ++i} return -1}
function lastIndexOf(y, xs) {let i = 0, j = -1; for (const x of xs) {if (x === y) j = i; ++i} return j}
function includes(y, xs) {for (const x of xs) if (x === y) return true; return false}
function reduce(a, fn, xs) {for (const x of xs) a = fn(a, x); return a}
const scan = G(function*(a, fn, xs) {for (const x of xs) {a = fn(a, x); yield a}})
const scan1 = G(function*(fn, xs) {
  xs = xs[Symbol.iterator]()
  let x = xs.next()
  if (!x.done) {
    let a = x.value
    yield a
    while (!(x = xs.next()).done) {
      a = fn(a, x.value)
      yield a
    }
  }
})
function inject(a, fn, xs) {for (const x of xs) fn(a, x); return a}
function forEach(fn, xs) {for (const x of xs) fn(x)}
function drain(xs) {for (const x of xs) {}}

function first(xs) {if (Array.isArray(xs)) return xs[0]; for (const x of xs) return x}
const head = first
function last(xs) {if (Array.isArray(xs)) return xs[xs.length - 1]; let z; for (const x of xs) z = x; return z}
function tail(xs) {return drop(1, xs)}
function init(xs) {return dropLast(1, xs)}

function count(xs) {if (Array.isArray(xs)) return xs.length; let i = 0; for (const x of xs) ++i; return i}
function pick(i, xs) {if (Array.isArray(xs)) return xs[i]; if (i < 0) return; for (const x of xs) if (i-- <= 0) return x}

function sum(xs) {return reduce(0, (x, y) => x + Number(y), xs)}
function mean(xs) {
  let count = 0, sum = 0
  for (const x of xs) {
    sum += Number(x)
    ++count
  }
  return sum / count
}
function product(xs) {return reduce(1, (x, y) => x * y, xs)}
function max(xs) {return reduce(-Infinity, Math.max, xs)}
function min(xs) {return reduce(Infinity, Math.min, xs)}
function minMax(xs) {
  let min = Infinity, max = -Infinity
  for (const x of xs) {
    const b = +x
    if (b < min) min = b
    if (b > max) max = b
  }
  return [min, max]
}

function groupBy(fn, unique = false, xs) {
  if (xs === undefined) {xs = unique; unique = false}
  return inject(new Map, unique ? (m, x) => {
    const k = fn(x), s = m.get(k)
    if (s) s.add(x)
    else m.set(k, new Set([x]))
  } : (m, x) => {
    const k = fn(x), l = m.get(k)
    if (l) l.push(x)
    else m.set(k, [x])
  }, xs)
}
function keyBy(fn, xs) {
  return inject(new Map, (m, x) => m.set(fn(x), x), xs)
}

const unique = G(function*(xs) {
  const used = new Set
  for (const x of xs) {
    if (!used.has(x)) {
      yield x
      used.add(x)
    }
  }
})

function toArray(xs) {return Array.from(xs)}
function toMap(xs) {return new Map(xs)}
function toSet(xs) {return new Set(xs)}
function toObject(empty = false, xs) {
  if (xs === undefined) {xs = empty; empty = false}
  const o = empty ? Object.create(null) : {}
  for (const [k, v] of xs) {
    o[k] = v
  }
  return o
}
const intersperse = G(function*(sep, xs) {
  let use = false
  for (const x of xs) {
    if (use) yield sep
    yield x
    use = true
  }
})
function join(sep = ',', xs) {
  if (xs === undefined) {xs = sep; sep = ','}
  let s = ''
  if (sep) {
    let use = false
    for (const x of xs) {
      if (use) s += sep
      s += x
      use = true
    }
  } else {
    for (const x of xs) s += x
  }
  return s
}

const slice = G(function*(xs, start = 0, end) {
  if (Array.isArray(xs)) {
    const len = xs.length
    if (start < 0) start += len
    if (end === undefined) end = len
    else if (end < 0) end += len
    if (start < 0) start = 0
    if (end > len) end = len
    for (let i = start; i < end; ++i) yield xs[i]
  } else if (end === undefined) {
    yield* start < 0 ? takeLast(-start, xs) : drop(start, xs)
  } else if (start >= 0) {
    let i = 0
    if (end === 0) return
    else if (end > 0) {
      for (const x of xs) {
        if (i >= start) yield x
        if (++i >= end) return
      }
    } else {
      // yield* dropLast(-end, drop(start, xs))
      const list = []
      const n = -end
      for (const x of xs) {
        if (i >= start) {
          const k = (i - start) % n
          if (i - start >= n) yield list[k]
          list[k] = x
        }
        ++i
      }
    }
  } else {
    // yield* dropLast(-end, takeLast(-start, xs))
    const list = []
    let n = -start
    let i = 0
    for (const x of xs) list[i++ % n] = x
    if (n > list.length) n = list.length
    let len
    if (end >= 0) {
      if (end > i) end = i
      len = end - (i - n)
    } else {
      len = n + end
    }
    for (let j = 0; j < len; j++) yield list[(i + j) % n]
  }
})

class Iter {
  constructor(iter) {this.iter = iter}
  [Symbol.iterator]() {return this.iter}
  next() {return this.iter.next()}
  toArray() {return Array.from(this.iter)}
  toMap() {return new Map(this.iter)}
  toSet() {return new Set(this.iter)}
  toObject(empty) {return toObject(empty, this.iter)}
  join(sep) {return join(sep, this.iter)}
  intersperse(sep) {return intersperse(sep, this.iter)}

  fork(n) {return fork(n, this.iter)}
  repeat(n) {return repeat(n, this.iter)}
  cycle() {return cycle(this.iter)}
  enumerate() {return enumerate(this.iter)}
  map(fn) {return map(fn, this.iter)}
  flatMap(fn) {return flatMap(fn, this.iter)}
  tap(fn) {return tap(fn, this.iter)}
  filter(fn) {return filter(fn, this.iter)}
  reject(fn) {return reject(fn, this.iter)}
  concat(...xss) {return concat(this.iter, ...xss)}
  push(...xs) {return push(...xs, this.iter)}
  unshift(...xs) {return unshift(...xs, this.iter)}
  flatten() {return flatten(this.iter)}
  chunksOf(n) {return chunksOf(n, this.iter)}
  lookahead(n) {return lookahead(n, this.iter)}
  subsequences(n) {return subsequences(n, this.iter)}
  drop(n) {return drop(n, this.iter)}
  dropWhile(fn) {return dropWhile(fn, this.iter)}
  dropLast(n) {return dropLast(n, this.iter)}
  take(n) {return take(n, this.iter)}
  takeWhile(fn) {return takeWhile(fn, this.iter)}
  takeLast(n) {return takeLast(n, this.iter)}
  zip(...xss) {return zip(this.iter, ...xss)}
  transpose() {return transpose(this.iter)}
  parallel(...xss) {return parallel(this.iter, ...xss)}

  every(fn) {return every(fn, this.iter)}
  some(fn) {return some(fn, this.iter)}
  find(fn) {return find(fn, this.iter)}
  findLast(fn) {return findLast(fn, this.iter)}
  findIndex(fn) {return findIndex(fn, this.iter)}
  findLastIndex(fn) {return findLastIndex(fn, this.iter)}
  indexOf(x) {return indexOf(x, this.iter)}
  lastIndexOf(x) {return lastIndexOf(x, this.iter)}
  includes(x) {return includes(x, this.iter)}
  reduce(a, fn) {return reduce(a, fn, this.iter)}
  scan(a, fn) {return scan(a, fn, this.iter)}
  scan1(fn) {return scan1(fn, this.iter)}
  inject(a, fn) {return inject(a, fn, this.iter)}
  forEach(fn) {return forEach(fn, this.iter)}
  drain() {return drain(this.iter)}

  first() {return first(this.iter)}
  head() {return head(this.iter)}
  last() {return last(this.iter)}
  tail() {return tail(this.iter)}
  init() {return init(this.iter)}
  count() {return count(this.iter)}
  pick(i) {return pick(i, this.iter)}

  sum() {return sum(this.iter)}
  mean() {return mean(this.iter)}
  product() {return product(this.iter)}
  max() {return max(this.iter)}
  min() {return min(this.iter)}
  minMax() {return minMax(this.iter)}

  groupBy(fn, unique) {return groupBy(fn, unique, this.iter)}
  keyBy(fn) {return keyBy(fn, this.iter)}
  unique() {return unique(this.iter)}

  slice(start, end) {return slice(this.iter, start, end)}
}
class ForkSource {
  constructor(n, iter) {
    this.iter = iter
    this.derived = Array(n)
    for (let i = this.derived.length; i--;) {
      this.derived[i] = new ForkIter(this)
    }
  }
  [Symbol.iterator]() {return this.derived[Symbol.iterator]()}
  pull() {
    const {done, value} = this.iter.next()
    if (done) return
    for (const b of this.derived) b.push(value)
  }
}
class ForkIter extends Iter {
  constructor(source) {
    super()
    this.iter = this
    this.buffer = []
    this.source = source
  }
  [Symbol.iterator]() {return this}
  push(v) {this.buffer.push(v)}
  next() {
    if (!this.buffer.length) this.source.pull()
    return this.buffer.length ? {done: false, value: this.buffer.shift()} : {done: true}
  }
}

Object.assign(module.exports = from, {
  is, from, generator, empty,
  range, irange,
  replicate, forever, iterate,
  entries, keys, values,
  toArray, toMap, toSet, toObject,
  intersperse, join,

  fork, repeat, cycle, enumerate,
  map, tap, flatMap, filter, reject,
  concat, push, unshift, flatten,
  chunksOf, lookahead, subsequences,
  drop, dropWhile, dropLast,
  take, takeWhile, takeLast,
  zip, transpose, parallel,
  every, some,
  find, findLast, findIndex, findLastIndex, indexOf, lastIndexOf, includes,
  reduce, scan, scan1, inject, forEach, drain,
  first, head, last, tail, init,
  count, pick,
  sum, mean, product, min, max, minMax,
  groupBy, keyBy, unique,
  slice,
})
}
  Parcel.main = file_$2fUsers$2fmertnuhoglu$2fprojects$2fstudy$2fjs$2fex$2fstudy_parcel_redux$2fex$30$32$2findex$2ejs; Parcel.makeRequire(null)()
  if (typeof module !== 'undefined') module.exports = Parcel.main.module && Parcel.main.module.exports
}(typeof global !== "undefined" ? global : typeof window !== "undefined" ? window : this)
