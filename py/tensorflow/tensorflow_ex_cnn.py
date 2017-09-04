#!/cm/shared/anaconda3/bin/python

import tensorflow as tf
from tensorflow.examples.tutorials.mnist import input_data

mnist = input_data.read_data_sets("tmp/mnist",one_hot=True)


n_classes=10
batch_size=100

x=tf.placeholder('float',[None,784])
y=tf.placeholder('float')

def neural_network_model(data):
	
        # her bir filtreyle yeni bir bilgi kazanıyoruz
        # her filtre belirli bir tip lokal bilgi çıkartıyor
        # 32 farklı tipte bilgi çıkartıyorsun
        # neden 5,5,1,32'de 1 var, çünkü RGB değil, BW resim
        # W2'de neden 32x64
        # W1'den sonra 32 tane imaj var gibi olacak
        # her imajı 32 çeşit filtreden geçirdim
        # her convolution layer'da genişleme oluyordu, bu 32 channel'dan oluyor
        # 64 ne?
        # W2'de 64 tane filtrem var, bunların her biri önceki layerdaki channellara ayrı ayrı işliyor
        # slide 12: doğrusu 3 tane summation olacak
        # K filtrem. m,n,channel (c) olsun
        # I sub c olacak
        # resimdeki parametrelerle: 3,3,3,2
        # W2'de 32, W1'deki 32 channel'ın katı olmak zorunda
	weights = {'W_conv1':tf.Variable(tf.random_normal([5,5,1,32])),
		   'W_conv2':tf.Variable(tf.random_normal([5,5,32,64])),
		   'W_fc':tf.Variable(tf.random_normal([7*7*64,1024])),
		   'W_out':tf.Variable(tf.random_normal([1024,n_classes])),
		   }

	biases = {'b_conv1':tf.Variable(tf.random_normal([32])),
		  'b_conv2':tf.Variable(tf.random_normal([64])),
		  'b_fc':tf.Variable(tf.random_normal([1024])),
		  'b_out':tf.Variable(tf.random_normal([n_classes])),
		}

        # data:
        # trX, trY = mnist.train.next_batch(200)
        # trX.shape
        # [200, 784]
        # -1, 200 parametresini sen hallet demek
	x =tf.reshape(data,shape=[-1,28,28,1])	
        # convolution operasyonu yapılıyor
        # [1,2,2,1] 2 ve 2 2d bir resmin x ve y eksenlerinde 2'şerli kayarak yapmak anlamına geliyor
        # 1. argman: batch
        # 4. argüman: in_channels
        # padding = "SAME"
        # orjinal input'la aynısını elde edecek şekilde padding yap
        # her bir output imaja özgü bir bias ekliyor
	conv1=tf.nn.conv2d(x,weights['W_conv1'],strides=[1,1,1,1],padding='SAME')+biases['b_conv1']
        # ksize: window boyutu. strides: kaydırma miktarları
        # pooling formülü: (w-f+2p)/s + 1
        # slide 29
	conv1=tf.nn.max_pool(conv1,ksize=[1,2,2,1],strides=[1,2,2,1],padding='SAME')
	

	conv2=tf.nn.conv2d(conv1,weights['W_conv2'],strides=[1,1,1,1],padding='SAME')+biases['b_conv2']
	conv2=tf.nn.max_pool(conv2,ksize=[1,2,2,1],strides=[1,2,2,1],padding='SAME')

        # pooling sırasında önce 28 -> 14'e, sonra 14 -> 7 pixele düşmüştü
        # 7 x 7 bilgisini buraya yazıyoruz
        # burada nn'deki tek boyutlu vektöre dönüştük
        # -1 -> 200 sample'a karşılık gelecek
	fc=tf.reshape(conv2,[-1,7*7*64])
	fc=tf.matmul(fc,weights['W_fc'])+biases['b_fc']
	fc=tf.nn.relu(fc)

        # 1024'lük nöron boyutunu burada 10'a indiriyoruz
	output = tf.matmul(fc,weights['W_out'])+biases['b_out']
	
	return output

def train_network_model(x):
	prediction = neural_network_model(x)
#	print(prediction)
	cost=tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=prediction,labels=y) )
	optimizer=tf.train.AdamOptimizer().minimize(cost)

	hm_epoch=10
	
	with tf.Session() as sess:
		sess.run(tf.global_variables_initializer())

		for epoch in range(hm_epoch):
			epoch_loss=0
			for _ in range(int(mnist.train.num_examples/batch_size)):
				epoch_x, epoch_y =mnist.train.next_batch(batch_size)
				_ , c = sess.run([optimizer,cost], feed_dict = {x: epoch_x , y: epoch_y } )
				epoch_loss += c
	
			print('Epoch', epoch, 'completed out of ', hm_epoch, 'loss', epoch_loss)
#		correct = tf.equal(tf.argmax(prediction,1),argmax(y,1))
#		accuracy = tf.reduce_mean(tf.cast(correct,'float'))
#		print('Accuracy', accuracy.eval({x:mnist.test.images, y:mnist.test.labels}))

train_network_model(x)
