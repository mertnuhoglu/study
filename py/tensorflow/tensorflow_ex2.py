#!/cm/shared/anaconda3/bin/python

import tensorflow as tf
from tensorflow.examples.tutorials.mnist import input_data

mnist = input_data.read_data_sets("tmp/mnist",one_hot=True)

n_nodes_hl1 = 500
n_nodes_hl2 = 400
n_nodes_hl3 = 300

n_classes=10
# elinde 60000 data set var, hepsinin üzerinden tanımlamak yerine 100'er 100'er train ediyor
batch_size=100

# tf'ye has. datasetleri verdiğin yer
x=tf.placeholder('float',[None,784])
y=tf.placeholder('float')

def neural_network_model(data):
	
        # her layer için bir dictionary oluşturmuş 
        # otpimize edeceğimiz şeyleri tf.Variable ile oluşturuyoruz
	hidden_1_layer = {'weights':tf.Variable(tf.random_normal([784, n_nodes_hl1])),
			 'biases':tf.Variable(tf.random_normal([n_nodes_hl1]))}

	hidden_2_layer = {'weights':tf.Variable(tf.random_normal([n_nodes_hl1, n_nodes_hl2])),
			 'biases':tf.Variable(tf.random_normal([n_nodes_hl2]))}

	hidden_3_layer = {'weights':tf.Variable(tf.random_normal([n_nodes_hl2, n_nodes_hl3])),
			 'biases':tf.Variable(tf.random_normal([n_nodes_hl3]))}

	output_layer   = {'weights':tf.Variable(tf.random_normal([n_nodes_hl3, n_classes])),
			 'biases':tf.Variable(tf.random_normal([n_classes]))}


        # tf.matmul = np.dot
	l1 = tf.add(tf.matmul(data,hidden_1_layer['weights']),hidden_1_layer['biases'])
	l1 = tf.nn.relu(l1)

	l2 = tf.add(tf.matmul(l1,hidden_2_layer['weights']),hidden_2_layer['biases'])
	l2 = tf.nn.relu(l2)

	l3 = tf.add(tf.matmul(l2,hidden_3_layer['weights']),hidden_3_layer['biases'])
	l3 = tf.nn.relu(l3)

	output = tf.matmul(l3,output_layer['weights'])+output_layer['biases']

	return output

def train_network_model(x):
	prediction = neural_network_model(x)
#	print(prediction)
        # cost fonksiyonu
	cost=tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=prediction,labels=y) )
	optimizer=tf.train.AdamOptimizer().minimize(cost)

	hm_epoch=10
	
	with tf.Session() as sess:
		sess.run(tf.global_variables_initializer())

		for epoch in range(hm_epoch):
			epoch_loss=0
                        # bir batch kadar data al
			for _ in range(int(mnist.train.num_examples/batch_size)):
                                # x: input y: label
				epoch_x, epoch_y =mnist.train.next_batch(batch_size)
                                # feed_dict ile data'ları gönderiyoruz
				_ , c = sess.run([optimizer,cost], feed_dict = {x: epoch_x , y: epoch_y } )
				epoch_loss += c
	
			print('Epoch', epoch, 'completed out of ', hm_epoch, 'loss', epoch_loss)
#		correct = tf.equal(tf.argmax(prediction,1),argmax(y,1))
#		accuracy = tf.reduce_mean(tf.cast(correct,'float'))
#		print('Accuracy', accuracy.eval({x:mnist.test.images, y:mnist.test.labels}))

train_network_model(x)
