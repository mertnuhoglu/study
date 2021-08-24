  <url:file:///~/Dropbox/mynotes/content/articles/articles_ai.md>

_ id=r_lastid ai_0001

# ref

# Siraj Raval

## The Math of Intelligence

    01-Intro - The Math of Intelligence-xRJCOz3AfYY.mp4
      teach from fundamentals
        not using any tool like Tensorflow
      labeled data (supervised learning)
        input:
          Domain X: every point of X has features that we observe
          Label set Y
          Data: a set f labeled examples
            S = {(x1,y1), ..., (xn, yn)} ⊆  X ⨯ Y
        output:
          A prediction rule/hypothesis
            h:X → Y
      Algorithm: Learn Something
        Input: Data, Mental Model
        Output: Updated Mental Model
        while Mental Model Makes Bad Predictions do
          make a guess at answer
          measure error
          if error is acceptably small then
            break
          else
            propose model adjustment
            Mental Model <- Mental Model + adjustment
      Choose from possible Models:
        Regression
        Classification
        Clustering

# Deep Learning - Andrew Ng id=g10151

    Deep Learning - Andrew Ng <url:file:///~/Dropbox/mynotes/content/articles/articles_ai.md#r=g10151>
    Week 2
      Binary Classification
        implementational techniques
          ex: m examples
            nn: with no for loop 
          ex: forward propagation followed by backward propagation
          using logistic regression
        logistic regression 
          algorithm for baniray classification
            ex: 
              input: image
              output
                1 -> cat
                0 -> non cat
              y: denotes output
              input image represented as
                rgb matrixes
                ex: 3 64x64 matrixes
              turn pixel values into feature vector
              unrow all these pixel values into x feature vector
              define x
                one dimensional vector
                that contains values of 3 matrixes
                64x64x3 = 12288 dimensions
                n = n_x = 12288 dimensions
              x -> y
          notation
            (x,y)
            x ∈ R^{n_x}, y ∈ {0,1}
            m training examples: (x^1, y^1), ..., (x^m, y^m)}
            m = m_{train}: number of training examples
            m_{test}: number of test examples
            X = [x^1 x^2 ... x^m]
              m columns: # training examples
              n_x rows: 
              keeping x^m as columns is easier to implement
            x ∈ R^{n_x⨯m}
              implementation in python:
                X-shape=(n_x,m)
            Y = [y^1 y^2 ... y^m]
            Y ∈ R^{1⨯m}
              Y-shape = (1,m)
      Logistic Regression
        learning algorithm
          where output is binary
        given x: feature vector
          you want an algorithm 
          that can output a prediction y-hat ŷ 
          ŷ : estimate for y
          ŷ = P(y=1|x)
            ex: given this image what is the chance that this is a cat picture
          given
            x ∈ R^{n_x}
            parameters of logistic regression:
              w ∈ R^{n_x}, b ∈ R
          how do we generate ŷ 
            if it was linear function of x:
              ŷ = w^T x + b
              this isn't good for binary classification
                because ŷ is probability that y = 1
                so 0 ≤ ŷ ≤ 1
                but w^T x + b can be out of this range
                which is meaningless for probability
            logistic regression:
              ŷ = σ(w^T x + b)
                = σ(z)
              where 
                σ(z) = \frac{1}{1+e^{-z}}
                  if z large σ(z) ≅ 1
                  if z small σ(z) ≅ 0
            note: keep w, b separate
              b: corresponds to inter-spectrum
      Cost Function
        to traing w and b, you need a cost function
        recap
          ŷ = σ(w^T x +b), where σ(z) = \frac{1}{1+e^{-z}}
          given training set {(x^1,y^1),...(x^m,y^m)}, want ŷ^i ≅ y^i in training set
          actually for training set:
            ŷ^i = σ(w^T x^i + b)
          note that .^i means: i-th example data
        loss (error) function:
          one option: squared error
            L(ŷ,y) = 1/2 (ŷ - y)^2
            but then optimization problem becomes non-convex (multiple optima)
          L(ŷ,y) = -(y log ŷ + (1-y) log(1-ŷ))
            intuition:
              if we use squared error, then you want it to be as small as possible
              this is same:
                if y = 1: L(ŷ,y) = - log ŷ
                  because (1-y) becomes zero
                  so you want log ŷ to be large
                  so you want ŷ to be large
                  so you want: ŷ = 1
                if y = 0: L(ŷ,y) = - log(1-ŷ)
                  you want log 1-ŷ large
                  ⇒  you want ŷ small
        cost function
          loss function: defined on single training example
          cost function: defined over all examples
          J(w,b) = 1/m \sum_{i=1}^m L(ŷ^i,y^i)
            = -1/m \sum[y^i log ŷ^i + (1-y^i) log (1-ŷ^i)]
      Gradient Descent
        want to find w,b that minimize J(w,b)
        ex: for J(w)
          Repeat {
            w := w - α \frac{d J(w)}{dw}
              where α: learning rate
          }
            do this until it converges
            convention: dw is used instead of \frac{dJ(w)}{dw}
              in code
        ex: for J(w,b)
          Repeat {
            w := w - α \frac{d J(w,b)}{dw}
            b := b - α \frac{d J(w,b)}{db}
          }
            convention: ∂ partial derivative
              instead of \frac{d J(w,b)}{dw}
              use \frac{∂ J(w,b)}{∂w}
            code vonvention: 
              dw = \frac{∂ J(w,b)}{∂w}
      Computation Graph
        computation graph explains why
          first forward propagation
          then back propagation
        ex
          J(a,b,c) = 3(a+bc)
          this consists of 3 steps:
            u = bc
            v = a + u
            J = 3v
          we can draw them as computation graph:
            b,c -> u = bc
            a,u -> v = a + u
            v -> J = 3v
          back propagation
            J -> v -> a, u -> b,c
        computation graph organizes forward propagation
        one step of backward propagation on a computation graph yields derivative of final output variable
      Derivatives with a Computation Graph
        ex:
          a = 5
          b = 3
          c = 2
          b,c -> u = bc = 6
          a,u -> v = a + u = 11
          v -> J = 3v = 33
        ex: back propagation
          what is \frac{dJ}{dv} = ?
          J = 3v
          v = 11 -> 11.001
          J = 33 -> 33.003
          f(a) = 3a ⇒  dJ/dv = 3
          note: dJ/dv requires one step back
        ex: what is dJ/da = ?
          a = 5 -> 5.001
          v = 11 -> 11.001
          J = 33 -> 33.003
          analytically
            dJ/da = dJ/dv dv/da = 3 * 1
            dv/da = 1
        Final output variable:
          J in this case
          \frac{d FinalOutputVar}{d var}
          convention: naming this variable
            dvar instead of dJdvar
      Logistic Regression Gradient Descent
        recap
          z = w^T x + b
          ŷ = a = σ(z)
          L(a,y) = -(y log(a) + (1-y) log(1-a))
        write this as computation graph
          ex: we have two features: x1, x2 
            x_1 w_1 x_2 w_2 b -> z = w_1 x_1 + w_2 x_2 + b
            z -> ŷ = a = σ(z)
            ŷ -> L = -(y log(a) + (1-y) log(1-a))
            backpropagation
              1. step
                \frac{dL}{da}
                  in code: da
                da = -y/a + (1-y) / (1-a)
              2. step
                dz = a - y
                  = dL/da * da/dz
                    where da/dz = a (1-a)
              3. step
                dw_1 = x_1 dz
                dw_2 = x_2 dz
                db = dz
              updates:
                w_1 := w_1 - α dw_1
                w_2 := w_2 - α dw_2
                b := b - α db
        this was for one data example
          next: how to do it for m examples
      Gradient descent on m examples
        recap
          cost function J
            J(w,b) = 1/m \sum L(a^i, y)
              where a^i = ŷ^i = σ(z^i) = σ(w x^i + b)
          ∂J/∂w_1 = 1/m \sum ∂/∂w_1 L(a^i, y^i)
            where dw_1^i = x_1^i dz
        logistic regression on m examples
          J = 0, dw_1 = 0, dw_2 = 0, db = 0
          for i = 1 to m
            z^i = w x^i + b
            a^i = σ(z^i)
            J = J - [y^i log a^i + (1-y^i) log(1-a^i)]
            dz^i = a^i - y^i
            dw1 = dw1 + x1^i dz^i
            dw2 = dw2 + x2^i dz^i
            db = db + dz^i
          J = J/m, dw1 = dw1/m, dw2 = dw2/m, db = db/m
          # note: dw1 dw2 db used as accumulators
            they don't have indices
            because they are as sum of all training examples
            whereas in contrast dz^i is wrt single training example
          dw1 = \frac{∂J}{∂w_1}
          w1 := w1 - α dw1
          w2 := w2 - α dw2
          b := b - α db
          # this is one step of gradient descent
        two weaknesses of calculations
          we need two for loops
            for i = 1 to m (all training examples)
            dw1 dw2 ... (all features)
          having explicit for loops
            makes algorithm less efficiently
            solution: vectorization
              pre-dl: vectorization was nice to have
      Vectorization
        goal: getting rid of explicit for loops
          why?
            we have too big data
            the code should run efficiently
            we need parallelization
        what is vectorization?
          z = w^T x + b
            where w and x are vectors, w ∈ R^n_x x ∈ R^n_x
          to compute w^T x in non-vectorized way:
            z = 0
            for i in range(n_x):
              z = z + w[i] * x[i]
            z = z + b
          vectorized implementation:
            z = np.dot(w,x) + b
        gpu vs cpu
          both have parallelization instructions
            SIMD: single instruction multiple data
      More Vectorization Examples
        rule: avoid explicit for-loops
          but not always possible
        ex
          u = Av
          u_i = \sum_i A_{ij} v_j
          for loop
            u = np.zeros((n,1))
            for i ...
              for j ...
                u[i] += A[i][j] * v[j]
          vectorized
            u = np.dot(A,v)
        vectors and matrix valued functions
          for loop
            u = np.zeros((n,1))
            for i in range(n):
              u[i] = math.exp(v[i])
          vectorized
            import numpy as np
            u = np.exp(v)
          similar functions:
            np.log
            np.abs
            np.maximum(v,0)
            v**2
            1/v
        logistic regression derivatives
          for loop 
            J = 0, dw_1 = 0, dw_2 = 0, db = 0
            for i = 1 to m
              z^i = w x^i + b
              a^i = σ(z^i)
              J = J - [y^i log a^i + (1-y^i) log(1-a^i)]
              dz^i = a^i - y^i
              dw1 = dw1 + x1^i dz^i
              dw2 = dw2 + x2^i dz^i
              db = db + dz^i
            J = J/m, dw1 = dw1/m, dw2 = dw2/m, db = db/m
          vectorized: features
            J = 0, dw = np.zeros((n_x, 1)), db = 0
            for i = 1 to m:
              z^i = w^T x^i + b
              a^i = σ(z^i)
              J += -[y^i log ŷ^i + (1-y^i) log(1 - ŷ^i)]
              dz^i = a^i (1-a^i)
              dw += x^i dz^i
              db += dz^i
            dw /= m
      Vectorizing Logistic Regression
        we try to compute:
          for first training example:
            z^1 = w^T x^1 + b 
            a^1 = σ(z^1)
          for second training example:
            z^2 = w^T x^2 + b 
            a^2 = σ(z^2)
          for third training example:
            z^3 = w^T x^3 + b 
            a^3 = σ(z^3)
        remember
          X = [x^1 x^2 ... x^m]
            (nx,m) matrix
            R^{n_x ⨯ m}
          we will compute
            [z^1 z^2 ... z^n] 
          this can be expressed as
            Z = [z^1 z^2 ... z^n] = w^T X + [b b ... b]
              = w^T [x^1 ... x^m]
              = [w^T x^1 + b , w^T x^2 + b ... , w^T x^m + b]
          numpy
            Z = np.dot(w.T, X) + b
              where w.T is w transpose
            # this is called broadcasting in python
          what about a values?
            A = [a^1 a^2 ... a^m]
              = σ(Z)
      Vectorizing Logistic Regressions Gradient Output
        math
          dz^1 = a^1 - y^1
          dz^2 = a^2 - y^2
          ...
          dZ = [dz^i dz^2 ... dz^m]
          A = [a^1 ... a^m]
          Y = [y^1 ... y^m]
          dZ = A - Y = [a^1 y^1, a^2 y^2 ...]
        we still had second for loop 
          dw = 0
          dw += x^1 dz^1
          dw += x^2 dz^2
          ...
          dw /= m
          # and
          db = 0
          db += dz^1
          db += dz^2
          ...
          db /= m
          # but at least dw is a vector
        what is db? it is summing up
          db = 1/m \sum dz^i
          python
            db = 1/m np.sum(dZ)
        dw?
          dw = 1/m X dZ^T
             = 1/m [x^1 ... x^m] [dz^1 ... dz^m]
             = 1/m [x^1 dz^1 + ... + x^m dz^m]
        vectorized implementation
          previously
            J = 0, dw = np.zeros((n_x, 1)), db = 0
            for i = 1 to m:
              z^i = w^T x^i + b
              a^i = σ(z^i)
              J += -[y^i log ŷ^i + (1-y^i) log(1 - ŷ^i)]
              dz^i = a^i (1-a^i)
              dw += x^i dz^i
              db += dz^i
            dw /= m
          vectorized training examples:
            Z = np.dot(w.T, X) + b
            A = σ(Z)
            dZ = A - Y
            dw = 1/m X dZ^T
            db = 1/m np.sum(dZ)
            w := w - α dw
            b := b - α db
          this is one step of gradient descent
            we will have an outer for loop
      Broadcasting in Python
        assume that you add:
          (m,n) matrix with (m,1) matrix
          then numpy automatically converts (m,1) matrix to (m,n) by duplicating columns
        same works with (1,n) matrix
        this works with + - / * operations
        same works with:
          (m,1) matrix and an simple number (1x1 matrix)
      A note on numpy vectors
        costs/benefits of broadcasting
          pro
            increased expressivity
          con
            introducing subtle bugs
              ex
                add column vector + row vector
                  expect: type error
                  result: duplicates rows/columns
        ex
          a = np.random.randn(5)
          print(a)
          [-2.07466393 -1.42358358 -0.06499076 -1.83822409 -0.17734999]
          print(a.shape)
          (5,)
          # this is called a rank 1 array
          # neither a row nor a column vector
          # transpose is the same
          print(a.T)
          [-2.07466393 -1.42358358 -0.06499076 -1.83822409 -0.17734999]
          # dot product is a number
          print(np.dot(a,a.T))
          9.7455652578
        ex: instead of rank 1, use rank 2 arrays:
          a = np.random.randn(5,1)
          print(a)
          [[-0.01554998]
           [-1.8951361 ]
           [ 0.74293626]
           [-0.71214972]
           [ 1.03228842]]
          # this is a 5x1 matrix (column vector)
          print(a.T)
          [[-0.01554998 -1.8951361   0.74293626 -0.71214972  1.03228842]]
          # this is 1x5 matrix (row vector)
          print(np.dot(a,a.T))
          [[  2.41801799e-04   2.94693237e-02  -1.15526421e-02   1.10739121e-02
             -1.60520616e-02]
           [  2.94693237e-02   3.59154085e+00  -1.40796533e+00   1.34962064e+00
             -1.95632705e+00]
           [ -1.15526421e-02  -1.40796533e+00   5.51954286e-01  -5.29081848e-01
              7.66924497e-01]
           [  1.10739121e-02   1.34962064e+00  -5.29081848e-01   5.07157222e-01
             -7.35143907e-01]
           [ -1.60520616e-02  -1.95632705e+00   7.66924497e-01  -7.35143907e-01
              1.06561938e+00]]
          # this is outer product
        recap:
          # don't use:
          a = np.random.randn(5)
            data structure:
            a.shape = (5,)
            rank 1 array
          # instead use:
          a = np.random.randn(5,1)
            a.shape = (5,1)
            column vector
          a = np.random.randn(1,5)
            row vector
        use asserts:
          assert(a.shape == (5,1))
          # serves as documentation too
        to convert rank 1 array to vector:
          a.reshape((5,1))
      Quick tour of jupyter notebooks
      Explanation of logistic regression cost function
        why we choose logistic regression function?
        recap
          ŷ = σ(w^Tx+b) where σ(z) = \frac{1}{1+e^{-z}}
          interpret ŷ = P(y=1|x)
          another way to say that:
            if y = 1: P(y|x) = ŷ
            if y = 0: P(y|x) = 1 - ŷ
        take these two equations
          summarize them as single equation
          P(y|x) = ŷ^y (1-ŷ)^{(1-y)}
          take log
          log P(y|x) = log ŷ^y (1-ŷ)^{(1-y)} 
            = y log ŷ + (1-y) log (1-ŷ)
            = - L(ŷ,y)
        this was loss function for single example
        what about m examples?
          p(labels in training set) = \prod p(y^i|x^i)
          log p(labels in training set) = log \prod p(y^i|x^i)
          log p(..) = \sum log p(y^i|x^i)
            = \sum -L(ŷ^i, y^i)
          J(w,b) = 1/m \sum L(ŷ^i,y^i)
    Understanding Activation Functions in Neural Networks
      https://medium.com/the-theory-of-everything/understanding-activation-functions-in-neural-networks-9491262884e0
      activation functions
        what does a neuron do?
          calculates a weighted sum of its input
          adds a bias
          then decides whether it should be fired or not
            activation function does this
        consider a neuron
          Y = \sum (weight * input) + bias
      step function
        how about a threshold based activation function?
          if Y is above some value, declare it activated
        drawbacks
          suppose you make a binary classifier
          if more than 2 classes, it will be difficult
      linear function
        A = cx
        drawbacks
          gradient descent: derivative is constant
          thus gradient has no relationship with x
          no matter how many layers
            final activation function is a linear function of the input of first layer
      sigmoied function
        = = \frac{1}{1+e^{-x}}
        benefits
          nonlinear
            combinations are also nonlinear
            we can stack layers
          binary activations ok
          has smooth gradient
          output always in range (0,1)
        drawbacks
          very little change of Y in extreme ends
            vanishing gradients
    reshape function
    week 2 - assignment
      02
        train_set_x_orig, train_set_y, test_set_x_orig, test_set_y, classes = load_dataset()
    Week 3
      what is a neural network
        /Users/mertnuhoglu/Dropbox/public/img/ss-214.png
        x1, x2, x3 -> neuron -> ŷ = a
        corresponds to computing graph
          x, w, b -> z = w^T x + b -> a = σ(z) -> L(a,y)
        stack (layers) of nodes:
          w^1 b^1 z^1: first layer
      Neural Network Representation
        names for layers:
          input layer: x1, x2, x3
          hidden layer
          output layer
      Computing a Neural Network's Output
      Vectorizing across multiple examples
      Explanation for Vectorized Implementation
      Activation functions
      Why do you need non-linear activation functions?
      Derivatives of activation functions
      Gradient descent for Neural Networks
      Backpropagation intuition
      Random Initialization
    Week 4
      Deep L-layer neural network
      Forward Propagation in a Deep Network
      Getting your matrix dimensions right
      Why deep representations?
        what is deep network?
          ex: face recognition
            first layer: feature detector or edge detector
              small areas
            second layer: eye, nose detector
          early layers: learn simple features
          late layers: complex functions
            larger areas
          ex: audio
            early layers: low level audio features
            later layers: phonemes: c a t
            later layers: words
            later: sentences
        circuit theory and deep learning
          small but deep nn can compute functions
          that a shallow nn require exponentially more units
          ex: XOR network
      Building blocks of deep neural networks
      Forward and Backward Propagation
        forward
          input: a^{[l-1]}
          output: a^{[l]}, cache: z^{[l]}, w^{[l]}, b^{[l]}
          vectorized:
            Z^{[l]} = W^{[l]} · A^{[l-1]} + b^{[l}]
            A^{[l]} = g^{[l]}(Z^{[l]})
        backward
          input: da^{[l]}
          output: da^{[l-1]}, dW^{[l]}, db^{[l]}
          vectorized:
            dZ^{[l]} = dA^{[l]} * g^{[l]}'(Z^{[l]})
            dW^{[l]} = 1/m dZ^{[l]} · A^{[l-1]T}
            db^{[l]} = 1/m np.sum(dZ^{[l]}, axis = 1, keepdims = True)
            dA^{[l-1]} = W^{[l]T} · dZ^{[l]}
      Parameters vs Hyperparameters
        parameters: W, b 
        hyperparameters
          learning rate
          # iterations
          # hidden layer L
          # hidden units n_1, n_2
          choice of activation function
        later hyperparameters
          momentum, ...
      What does this have to do with the brain?
    idea: karmaşık bir sistem oluştur
      sonra bunun içindeki bazı değişkenlerin davranışını dl ile tahmin etmeye çalış
      sonra sistemi değiştir
      dl'in parametrelerindeki değişim nasıl oluyor?
        dl'nin katmanları neye karşılık geliyor?

# Serdar Tensorflow 20170903  id=g10152

    Serdar Tensorflow 20170903  <url:file:///~/Dropbox/mynotes/content/articles/articles_ai.md#r=g10152>
    ex:
      import tensorflow as tf
      from tensorflow.examples.tutorials.mnist import input_data
      mnist = input_data.read_data_sets("tmp/mnist", ..)
    tf'de row'lar sample, column'lar farklı feature (nöronlar)
    cs231n.github.io
      çok iyi bir sayfa
    Sinan Kalkan Deep Learning
      pdf
        CIFAR
        convolution operator
          correlation gibi bir şey
          bunu shift ettiriyor korelasyonu buluyor
          buna convolution deniyor
          imajlarda 2D olduğundan ona uygun tanımlanıyor korelasyon
        visionda filtrelerden geçiriyoruz
          mesela edge detectorler
            convolution operator ile yapılıyor
          cv'de uygun filtre yıllar sonunda bulundu
          fakat cnn'de o kısmı da otomatikleştiriyorsun
            en uygun filtreyi de sana buluyor
            birkaç convolutional layer gidiyorsun, sonra tipik nn layerlardan geçiriyorsun
              en başa koyuyorlar cl'leri
          normal ann'de her layer tek bir layer
            fakat cnn'de her layer bir stacked layer grubu aslında
            daha fazla channel olduğu için daha kalın oluyor layerlar
              channel'lara depth deniyor
            pooling: down sample gibi bir şey
          convolutionda ne yaptığı: cs231n'de anlatılıyor
            şekil: 7x7x3 imaj
              ref
                http://cs231n.github.io/assets/conv-demo/index.html
              3 channel var input layer
              x convolution h diyordu
                h dediği W 
              normalde W girdi n x çıktı n kadar ağırlıktan oluşuyordu
                burada parametre sayısı çok az
                3x3 7x7 gibi küçük filtreler yeterli
              filtreden geçirmek ne?
                (I*K)[i,j] = 
                K matrisini I matrisinin 5,7'şerlik matrisleri üzerinde kaydıra kaydıra çarpıyorsun element wise
                  her 3 channel için de yapıyorsun bu işlemi
              burada iki filtre var, bu durumda output layer'da 2 channel oluyor
            slide: 27
              ref: resim 231n
              bir padding yapıyor
                benim orjinal resmim 5x5
                padding yapınca, kenarlardan 1'er pixel 0 ekliyorsun. resim 7x7 oluyor
              atlama işlemi (stride)
                2 pixel yapmış burada
              diyelim ki, stride birer birer gitti, pad de yapmadı
                o zaman 5 ve 3'ü nasıl convolute ederdi
                  3 seferde yapardı
              bazen imajın boyutunu korumak istiyorlar, onun için pad yapıyorlar
          Pooling
            slide 43
            ex: max pooling
              her bir window'daki max değeri alıp, bir sonraki layera bunları aktarıyorsun
            layer'daki nöron sayısını azaltır (downsampling)
            max alınca, bazı şeylere karşı seni koruyor
          Normalization Layer
            slide 55
            deprecated, artık drop out yapılıyormuş
          Fully Connected Layer
            slide: 57
            CNN iyi, fakat normal nn'lerden biraz farklı
              
              

# Learning to See 20170907  id=g10153

    Learning to See 20170907  <url:file:///~/Dropbox/mynotes/content/articles/articles_ai.md#r=g10153>
    decision trees
      ex: finger detection
        /Users/mertnuhoglu/Dropbox/public/img/ss-215.png
        f(some_matrix) = ?
          how many fingers are there?
      1. step: reduce pixel values from 256 to 2
      ex: rule: check if 9x9 matrices match our expected finger matrix?
        /Users/mertnuhoglu/Dropbox/public/img/ss-216.png
      ex: rule: 30 different matrices all match
        detects nonfingers as finger as well
        confusion matrix
        /Users/mertnuhoglu/Dropbox/public/img/ss-217.png
      should we define new rules to improve prediction? or something else?
        1980s: wildly optimistic predictions
          herbert simon: 20 years 
        ai winter: 1974-1980
        big problem for DEC: components don't work together
          mcdermott solves the problem
            he asked the experts
            how would you solve this problem?
          expert knowledge -> code
            knowledge engineering
            = expert system
          ai explosion followed
          after 90s this explosion busted
        why did expert systems bust?
          huge number of rules 
            brittle
            costly
        expert system strengths
          proving math theorems
          diagnosing problems in recators
        these are hard for humans
          but easy for machines
          steven pinker
            main lesson of AI:
              hard problems are easy (logical deduction)
              easy problems are hard (vision)
            thus: engineers are in danger not waiters
          moracave's paradox
          no matter how many rules we define for finger detection
            our code won't be as strong as human visual cortex
        knowledge engineering assumes we know how we do something
          which is not true
        alternative approach to expert systems:
          machine learning
      part04
        40s arthur samuel: chess playing computer
          he didn't teach rules on how to play
          the program learns by itself
          it simulates several games by itself
        ml:
          instead of writing own rules
          write programs that learn rules by examples
        1. try: memorization
          compare examples directly
          if it matches it is finger
        how to compare confusion matrices?
          performance of an algorithm
          opt
            accuracy
              (true_positive + false_negative) / total
            recall
              true_positive / all_finger
            precision
              true_positive / predicted_as_finger
        problem with memorization
          overfitting to training data
      part05
        memorization ≠ learning
        test algorithms on examples it didn't see before
        to learn is to generalise
        ml ≅ search
          ml process is by unnderstanding search space
          how many possible rules are there
      part06
        how can we learn rules that generalizes from a limited set of examples
          what do have positive examples in common?
          what do have negative examples in common?
        how many distinct rules fit our data?
          /Users/mertnuhoglu/Dropbox/public/img/ss-218.png
      part07
        how many distinct rules fit our data?
          logical connectives: and or not
          truth tables
          /Users/mertnuhoglu/Dropbox/public/img/ss-219.png
        principles
          learning is generalisation
          machine learning is fundamentally ill-posed
            our examples don't uniquely determine rules
            we have to make assumptions
            futility of bias-free learning
      part08
        how humans learn?
          we focus on a few features only
            information that matters
            ignoring the rest
        shortcomings of human learning
          not always getting it right
          assuming we can ignore stuff
        they are also keys of machine learning
        ex:
          /Users/mertnuhoglu/Dropbox/public/img/ss-220.png
          does g1 generalize better than g4?
            yes, much better
            for this we need one new assumption:
              our training data is randomly sampled
                training data is statistically representative of data in large
            how this assumption helps?



