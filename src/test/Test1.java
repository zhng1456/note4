package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

import object.*;

public class Test1 {
	
	/*
	 * 1-8章
	 */
	
	/*
	 * 
	 * 第一章:
	 * 
	 * 	·java用三个关键字在类的内部设定边界:public,private,protected 
	 * 		·public表示紧随其后的元素对任何人都是可用的
	 * 		·private除了类型的创建者和类型的内部方法之外的任何人都不可以访问的元素
	 * 		·protected与private作用相当，差别在于继承的类可以访问protected成员，但是继承的类不能访问private成员，另外protected还具有包访问权限
	 * 
	 * 
	 * 	·继承
	 * 		·在创建一个类之后，即使另一个新类与其具有相似的功能(但还是有一些不同的地方)，你还是等创建一个新类。
	 * 			如果能以现有的类为基础，复制它，然后通过添加和修改这个 副本 来创建新类就要好多了。
	 * 			通过继承就可以达到这种效果，不过也有例外，当源类(被称为基类，超类或父类)发生变动时，"被修改的副本"(被称为导出类，继承类，或子类)也会变动
	 * 	
	 * 		·类型不仅仅只是描述了作用于一个对象集合上(一个类就是各种元素集合组成(也就是对象集合))，同时还有与其他类型之间的关系
	 * 		·一个基类包含其所有导出类型所共享的特性和行为，可以创建一个基类型来表示某些对象的核心概念，从基类中导出其他类型，来表示此核心可以被实现的各种不同方式
	 * 			(以垃圾回收机为例)，用来归类散落的垃圾，"垃圾"是基本类型，可以通过添加额外的特征(瓶子有颜色)或行为(铁罐可以被磁化)导出更具体的垃圾类型
	 * 			(以几何形为例子)，几何形可以包含圆形，多边形等，而多边形也包含正方形，长方形(所有基类就是几何形，派生出很多导出类，这样看起来非常有层次感)
	 * 			·可以通过使用继承来构建一个类型层次结构(已不仅仅是为了继承基类中的内容)，通过继承来建立一个庞大的体系，而看起来是非常有层次结构的
	 * 
	 * 		·所有可以发送给基类的信息同时也可以发送给导出类对象，导出类与基类具有相同的类型。(上面的例子，圆形和多边形(导出类)都属于几何形(基类))
	 *		
	 *		·两种方法是基类与导出类产生差异
	 *			·非常直接，在导出类中添加新方法(这些方法不是基类中的一部分)，这意味中基类不能满足你的所有需求，因此必须额外添加更多额外的方法
	 *			·但是有时候也应该仔细的考虑，是否存在可以把这些额外的方法再次抽象到基类中。(在这很大程度上就会引发使用下面的 "覆盖":重写基类的方法)
	 *				"覆盖"(在使用extends关键字表示继承的java中):改变基类中方法的行为(方法名不变，在导出类中实现)，使导出类和基类之间产生差异(使之满足导出类的需求)
	 *				要想覆盖某个方法，可以直接在导出类中建该方法的新定义即可(因为使用extends继承并不一定要实现基类中的所有方法，而是覆盖需要改变的方法即可)
	 *	
	 *
	 *	·伴随多态的可互换对象
	 *		·在处理类型的层次结构时，经常想把一个对象不当作它所属的特定类型对待，而是将其当作其基类的对象对待。这使得人们可以编写出不依赖于特定类型的代码
	 *
	 *		·一个非面向对象编程的编译器产生的函数调用会引起所谓的 "前期绑定"，这么做意味着编译器将产生对一个具体函数名字的调用，
	 *			而运行时将这个调用解析到将要执行的代码的绝对地址
	 *		·在面向对象中，程序直到运行时才能够确定代码的地址，所以当信息发送到一个泛化对象时(它并不知道具体的实现类是哪一个，调用的是那个类的方法)
	 *			为了解决这个问题，面向对象程序设计语言使用了 后期绑定 的概念。当向对象发送消息时，被调用的代码直到运行时才能确定下来。
	 *			编译器要确保被调用的方法存在，并对调用参数和返回值执行类型检查，但是并不知道将执行的确切代码
	 *			·为了执行后期绑定，java使用一小段特殊的代码来代替绝对地址调用，这段代码使用在对象中存储的信息来计算方法体的地址。这样，根据这一小段代码的内容，
	 *			每一个对象都可以具有不同的行为表现。当向每一个对象发送消息时，该对象就能够知道对这条消息应该做些什么。 
	 *
	 *		·把导出类看作是它的基类的过程称为向上转型。
	 *			
	 *
	 *	·单继承结构
	 *		·在单根继承结构中的所有对象都具有一个共用接口，所以它们归根到底都是一个基本类型(Object)
	 *		·单根继承结构中保证所有对象都具备某些功能。因此，你知道在你的系统中每个对象有那些操作
	 *		·单根继承结构使垃圾回收器的实现变得容易得多(这正是java相对c++的重要改进之一)
	 *		 
	 *
	 *	·容器(也称集合)
	 *		·在某些类库中，一两个通用的容器足够满足所有的需要，但是在其他类库中(例如java中)，具有满足不同需要的各种容器
	 *			例如:List(用于存储序列)，Map(也称为关联数组，用来建立对象之间的关联)，Set(每种对象类型只持有一个)
	 *				多个容器可以满足多个需求,还是需要对容器有是所选择，这里有两个原因:
	 *					·不同容器提供了不同类型的接口和外部行为
	 *					·不同的容器对于某些操作具有不同的效率(例如ArrayList访问元素快  ，LinkedList增删元素快)
	 *		
	 *
	 *	·参数化类型
	 *		·历史原因:
	 *			在SE5.0之前，容器存储的对象都只具有java的通用类型:Object。单根继承结构意味着所有东西都是Object类型，所有该容器可以存储任何东西
	 *			但是由于容器只存储Object，所以当将对象引入容器时，它必须被向上转型为Object，因此它丢失了身份，将它取出时取到的是一个Object引用
	 *			必须将该Object向下转型为更具体的类型。这种转型方式称为向下转型。向上转型是安全的，但是向下转型是不安全的(因为它不知道你具体的实现类是哪一个)
	 *		
	 *		·那么是否能创建一个容器，它知道自己所保存的对象类型，从而不需要向下转型以及消除犯错误的可能。
	 *			这样的解决方案被称为参数化类型机制，参数化类型就是一个编译器可以自动定制作用于特定类型上的类。例如使用参数化类型，编译器可以定制一个只接受和取出Dog对象的容器
	 *		
	 *		·SE5.0 的重大变化之一就是增加了参数化类型，在java中称为泛型。一对<>，中间包含类型信息。例如可以用下面这样的语句来创建一个存储Dog的ArrayList的容器
	 *			ArrayList<Dog> list = new ArrayList<Dog>();
	 *
	 *
	 *	·对象的创建和生命周期
	 *		·对象的数据位于何处?怎样控制对象的生命周期?C++认为 效率 控制最重要的议题，所以给程序员提供了选择的权力。
	 *			为了追求最大的执行速度，对象的存储空间和生命周期可以在编写程序是确定，这可以通过将对象置于堆栈或静态存储区域来实现。
	 *			这种方式将  存储空间分配和释放  放置于优先考虑的位置，在某些情况下这样的控制非常有价值。但是，也牺牲了灵活性，
	 *			因为必须在编写程序时知道对象的确切的数量，生命周期和类型。
	 *		·还有一种方式被称为  堆的内存池  中动态地创建对象(动态内存分配方式)。在这种方式中，直到运行时才知道需要多少对象，它们的生命周期以及类型是什么。
	 *			这些问题只能在程序运行时相关代码被执行到的那一刻才能确定。
	 *			如果需要一个新的对象，可以在需要的时刻直接在 堆 中创建。因为存储空间是在运行时被动态管理的，所以需要大量的时间在 堆 中分配存储空间，
	 *			但这可能要远远大于在 堆栈 中创建存储空间的时间(C++就是采用这种时间比较快的方式)
	 *		·java完全采用动态内存分配方式。每当想要创建新对象时，就要使用new关键字来构建此对象的动态实例
	 *		·还有一个议题，就是对象生命周期。
	 *			对于允许在 堆栈 上创建对象的语言，编译器可以确定对象存活时间，并自动销毁它。
	 *			然而在  堆  上创建对象，编译器就会对它的生命周期一无所知。在C++这样的语言中，必须通过编程的方式来确定何时销毁对象，不能正确处理可能会导致内存泄漏(C++常见的问题)
	 *			java提供了"垃圾回收器"的机制，它可以自动发现对象何时不再使用，并销毁它。这种机制减少了考虑的议题和必须编写的代码，更重要是提供更高层的保障，可避免暗藏的内存泄漏
	 *		·java的"垃圾回收器"被设计用来处理内存释放问题，它知道对象何时不再使用，并自动释放对象占用的内存
	 *			这一点是由于  所有对象都是继承自单根基类Object 以及 只能以一种方式创建对象(在堆上创建)  这两个特性结合起来，使得用java编程的过程较之用c++要简单得多
	 *		
	 *
	 *	·异常处理:处理错误
	 *		·异常是一种对象，它从出错地点被抛出，并  被  专门设计用来处理特定类型错误相应的异常处理器 "捕获"。
	 *		·异常处理就像是  与  程序正常执行路径  并行的，在错误发生时执行的另一条路径。因为它是另一条完全分离的执行路径，不会干扰到正常的代码
	 *			这往往使得代码代码编写的简单，因为不需要被迫定期检查错误。此外，被抛出的异常不像方法返回的错误值和方法设置的用来表示错误条件的标志位那样可以被忽略。
	 *		·异常不能被忽略，所以它保证一定会在某处等到处理。最后需要指出的是:异常提供了一种从错误状况进行可靠恢复途径。现在不再是只能退出程序，
	 *			你可以经常进行校正，并恢复程序的执行，这些都有助于编写出更健壮的程序。
	 *		·java异常处理在众多的编程语言中格外引人注目，因为java一开始就内置了异常处理，并强制你必须使用它。它是唯一可接受的错误报告方式。
	 *			如果没有编写正确的处理异常代码，那么就会得到一条编译时的出错信息。这种有保障的一致性有时会使错误处理非常容易
	 *		·值得注意的是，异常处理不是面向对象的特征--尽管在面向对象语言中异常常被表示成为一个对象。异常处理在面向对象语言出现之前就已经存在了。
	 */
	
	
	
	/*
	 * ·第二章：一切都是对象
	 * 
	 * 	·在java中一切都被视为对象，因此可采用单一固定的语法。尽管一切都看成对象，但操纵的标识符实际上是对象的一个"引用"。
	 * 		可以将此情形想象为"遥控器"(引用)与"电视机"(对象)，只要握住这个遥控器，就能保持与电视机连接.
	 * 		当想减少音量，实际操控的是"遥控器"(引用)，在由遥控器来控制"电视机"(对象)
	 * 		如果你想在房间里面到处走走，同时可以控制"电视机"，那么只需要携带"遥控器"(引用)，而不是电视机(对象)
	 * 	·此外，即使没有电视机，遥控器也可以独立存在，也就是说，你拥有一个引用，并不一定需要有一个对象与它关联。像这样创建一个引用:String s;
	 * 		但这里创建非只是引用，并不是对象。如果这是向s发送一个消息，就会返回一个运行时的 错误。因为这会s实际并没有与任何事物关联
	 * 
	 * 
	 * 	·必须由你创建所有对象
	 * 	 ·存储到什么地方
	 * 		·程序运行时，对象是怎么进行放置安排的呢?特别是内存是怎样分配的呢?有五个地方可以存储数据(java程序中的数据可能存储到的五个区域)
	 * 		  ·寄存器。这是最快的存储区，因为它位于不同于其他存储区的地方--处理去内部。但是寄存器的数量极其有限，所以寄存器根据需求进行分配。
	 * 			你不能控制，也不能在程序中感觉到寄存器存在的任何迹象(另一方面，C/C++允许您向编译器建议寄存器的分配地址)
	 * 		  ·堆栈。位于通用RAM(随机访问存储器)中，但是通过堆栈指针可以从处理器那里获得直接的支持。堆栈指针向下移动，则分配新的内存，向上移动，则释放那些内存。
	 * 			这是一种快速有效的分配存储的方法，仅次于寄存器。创建程序时，java系统必须知道存储在堆栈内所有项确切的生命周期，以便上下移动堆栈指针。这一约束限制了程序的灵活性，
	 * 			所以虽然某些java数据存储于堆栈中--特别是对象引用，但是java对象并不存储于其中
	 * 		  ·堆。一种通用的内存池(也位于RAM区)，用于存放所有的java对象。堆不同于 堆栈 的好处是:编译器不需要知道存储的数据在堆里存活的时间。因此在堆中分配存储有很大的灵活性。
	 * 			当需要一个对象是，只需要new写一行简单的带代码，当执行这行代码的时候，会自动在对  堆  中进行存储分配。
	 * 			当然，为了这种灵活性是要付出代价的:用 堆 进行存储分配和清理比在 堆栈(栈) 中更耗时。(c/c++可以在 栈 中分配内存，但是java不行)
	 * 		  ·常量存储。常量值通常直接存放在程序代码内部，这样做是安全的。因为它们永远不会被改变
	 * 		  ·非RAM存储。 如果数据完全存活于程序之外，那么它可以不受程序的任何控制，在程序没有运行时也可以存在。其中两个基本的例子是 流对象  和  持久化对象。
	 * 			·在流对象中，对象转化成字节流，通常被发送给另一台机器。
	 * 			·在"持久化对象"中，对象被存储在磁盘上，因此，程序被终结，它们也可以保持自己的状态。
	 * 			这种存储方式的技巧在于:把对象转化为可以存放在其他媒介上的事物，在需要时，可恢复成常规，基于RAM的对象。
	 * 	 		java提供轻量级持久化的支持，而想jdbc和hibernate这样的机制提供了更加复杂的。
	 * 	 ·特例:基本类型(不用由你创建的对象)
	 * 		·为什么不用你创建:在程序设计中经常用到一系列类型，它们需要特殊的对待(因为如果自己new的话，会在 堆 中存储这，这样的话聚会耗时),但是你会经常使用这些数据，所以要特殊对待
	 * 			是因为new将对象存储在"堆"里，故用new创建一个对象--一个特别小简单的变量，往往不是很有效(如果你打算把一个数字 1 new进去的话)
	 * 			因此，对于这些类型(比较简单的)，java采取C/C++相同的方法。也就是说，不用new来创建变量，而是创建一个并非是引用的变量(就是操作这个变量是不用引用的，就是不用通过遥控器进行操作)。
	 * 			这个变量直接存储"值"，并置于堆栈中，因此更加高效。
	 * 	 	·boolean类型所占存储空间的大小没有明确指定，仅定义为能够取字面值true或false
	 * 		·基本类型具有的包装类(包装类创建对象跟其他类是一样的，创建的一个非基本对象在 堆 中，而基本类型创建出来是在 堆栈 中的，而且是没有引用的)
	 * 			例如:
	 * 				char c = 'x';
	 * 				Character ch = new Character(c);
	 * 			也可以这样用:
	 * 				Character ch = new Character('x');
	 * 			java SE 5.0 的  自动包装功能  能将自动地将  基本类型  转换为  包装器类型(基本类型的数据在  堆栈  中， 包装器类型的数据在 堆  中)
	 * 				Character ch = 'x';
	 * 			并可以反向转换
	 * 				char c = ch;
	 * 
	 *		·高精度数字
	 *			·java提供两个用于高精度计算的BigInteger和BigDecimal.虽然它们大体上属于"包装器类"的范围，但二者都没有对应的基本类型
	 *				不过，这两个类包含的方法，提供的操作对  基本类型  所能执行的操作相似。也就是说，能作用于int或float的操作，也同样能够作用于这两个类上。
	 *				只不过是以方法调用的方式取代了运行符方式实现。由于这么做复杂了许多，所以运行速度会比较慢。在这里以精度换取了速度
	 *		·java中的数组
	 *			当创建一个数组对象时，实际上就是创建了一个引用数组，并且每个引用都会自动被初始化为一个特定值，该值拥有自己的关键字null。
	 *			一旦java看见null，就知道这个引用还没有指向某个对象。在使用任何引用之前，必须指定一个对象
	 *			如果使用一个还是null的引用，在运行时就会报错。
	 *			还可以创建用来存放基本类型的数组。同样，编译器也能确保这种数组的初始化，因为它会将这种数组初始化为0(基本类型没有引用)
	 *		
	 *
	 *	·永远不要销毁对象
	 *	 ·作用域:作用域决定了在其内定义的变量名的可见性和生命周期，在C，C++,java中，作用域由花括号的位置决定的。例如:
	 *		{  
	 *			int x = 12;
	 *			{
	 *				int y = 13;
	 *			}
	 *		}		
	 * 			在作用域定义的变量只可用于作用域结束之前。
	 * 	 ·对象的作用域
	 * 		·java对象不具备和基本类型一样额生命周期。当用new创建一个对象时，它就可以存活于作用域之外。例如:
	 * 			{
	 * 				String s = new String("a object");
	 * 			}
	 * 			引用s在作用域的终点(也就是花括号结束，是一个引用消失，不是对象消失)就消失了。然而，s指向的对象还在内存区域中，我们也无法访问这个对象(因为对它唯一的引用已经超出了作用域范围)
	 * 		·事实证明，由new创建的对象，只要你需要，就会一直保留下去。但这不会出现对象填满内存空间吗?(这是C++经常出现的问题)
	 * 			java有一个垃圾回收器，用来监视用new出来的对象，并辨别那些不会再被引用的对象，随后就会释放这些对象的内存空间。(所以你不用担心内存泄漏问题)
	 * 		 
	 * 	·基本成员默认值
	 * 	 ·如果类的某个成员是基本数据类型，即使没有进行初始化，java也会确保它获得一个默认值。
	 * 		当变量作为  类的成员变量  使用时，java才确保给定其默认值，以确保那些是基本类型的成员变量等到初始化，防止程序出错。
	 * 		但是，这些初始化的值可能是你不想要的，甚至是不合法的，所以最好对变量进行初始化。
	 * 		·注意:上述确保初始化的方法并不是适合于"局部"变量。所以，在某个方法定义中有:int x; 那么该变量等到的可能是任意值，而不会被自动初始化为零。
	 * 		所以在使用x前，应先对其赋一个适当的值。如果没有这么做，那么编译时就会返回一个错误，告诉你变量没有初始化。(这正是java优于C++的原因，C++只被视为警告，而java视为错误)
	 * 	  
	 * 	·java中的方法只能作为类的一部分来创建，方法只有通过对象才能被调用。(除了static,static方法并不依赖于对象的存在 )
	 * 	·方法名和参数列表 (被称为"方法签名") 唯一地标识出某个方法
	 * 	·字段和方法:一旦定义了一个类，就可以在类中设置两种类型的元素:字段和方法
	 * 					
	 * 	·static关键字:
	 * 		通过static关键字可以满足下面两个方面的需要:
	 * 		 ·只想为某特定域分配单一的存储空间，而不去考虑要创建多少对象，甚至根本不需要创建任何的对象
	 * 		 ·希望某个方法不与这个类型的任何对象相关联
	 *		例如:static int i = 47;
	 *	 ·有些面向对象语言(一个java文献也用到这些术语)使用  类数据  和  类方法  两个术语 表示使用了static关键字的数据
	 *
	 *
	 *	·注释文档的语法
	 *		·共有三种类型的注释文档(类，域，方法 )，都是位于前面(例如 类注释 在  定义类之前  要定义)
	 *		类的注释写在这
	 *		public class Document{
	 *			域的注释
	 *			public int i;
	 *			方法的注释
	 *			public void method(){}
	 *		}
	 *	·注意:javadoc只能为public和protected成语进行文档注释。private会被忽略(非要可见的话可以使用-private注解)
	 *	
	 *	·嵌入式HTML
	 *		javadoc通过生成的HTML文档传送HTML命令，这使你能够充分利用HTML 
	 *
	 *	·一些标签示例
	 *		@see:引用其他类(在文档中是以一个超链接的形式导去另一个类中)
	 *		@version：版本号
	 *		@author：作者
	 *		@since：最早使用的版本号
	 *		@param：参数 
	 *		@return：描述返回值的类型
	 *		@throws：对抛出的异常进行处理
	 *		@deprecated：指出一些旧特征已由改进的新特征取代
	 */
	
	//java的基本类型数组 和 包装器类型数组
	@Test
	public void array(){
		int[] a = new int[8];
		System.out.println("基本类型数组的初始化值是：" + a[0]);
		/*
		 * 基本数据类型的数组的初始值是 0
		 * 	基本数组类型的存储区域是 堆栈 (因为在这里的数据存储速度会很快，当你要经常使用这些数据的时候就会很方便)
		 */
		Integer[] b = new Integer[8];
		System.out.println("包装器类型数组的初始化值是：" + b[0]);
		/*
		 * 包装器类型的数组的初始值是 null
		 * 	这是包装类型，数据的存储区域是  堆  中，(这里是与其他的对象一样的，就是存储时比存储在  堆栈  中的快)
		 */
	}


	/*
	 * 第一个java程序
	 * 	·在每个程序的开头，必须声明import语句(每个java文件都默认导入java.lang)
	 * 	·java.lang中没有Date类，所以必须导入另一个类库才能使用它
	 * 	·选择java.lang，在选择System类.选择out字段，可以看见out是一个静态的PrintStream类型，因为是静态所以不需要创建任何东西，out对象就可以使用。
	 * 		但是out对象可以做什么(调用什么方法)，是由它的类型PrintStream决定的，直接看PrintStream里面有什么方法可用即可。
	 * 		PrintStream类中有一个println方法，可以将给你的数据打印到控制台
	 *  
	 * 这里模拟main方法
	 */
	@Test
	public void main(){
		System.out.println("Hello,it's:");
		System.out.println(new Date());
		System.getProperties().list(System.out); 
	}
	
	/*
	 * static关键字
	 */
	@Test
	public void staticWord(){
		ObjectA a1 = new ObjectA();
		ObjectA a2 = new ObjectA();
		System.out.println("对象a1的i值是" + a1.i);
		System.out.println("对象a2的i值是" + a2.i);
		ObjectA.i++;
		System.out.println("对象a1将i值是" + a1.i);
		System.out.println("对象a2中的i值" + a2.i);
	}
	
	
	/*
	 * 练习 
	 */
	ObjectB objectB;
	HelloData helloData;
	ATypeName aTypeName;
	DataOnly dataOnly;
	ObjectC objectC;
	Incrementable incrementable;
	ObjectD objectD;
	ObjectE objectE;
	
	/*
	 * ·第三章：操作符
	 * 	·使用java操作符
	 *	  ·基本所有的操作符都只能操作"基本类型"。但这些操作符能操作对象"="，"=="，"!="，除此之外，String类支持"+"和"+="
	 *
	 *	·算术操作符
	 *	  ·整数的除法会直接去掉结果的小数位，而不是四舍五入地圆整结果
	 *	  ·同时进行运算与赋值操作。例如:要将x加4，并将结果赋回给x，可以这么写:x+=4
	 *	  ·自动递增和递减(具体的区别可以看下面的例子):
	 *		·前缀式:先执行运行，在赋值
	 *		·后缀式：先赋值，再运行	
	 *	·关系操作符
	 *	  ·关系操作符生成的是一个boolean结果，是计算操作数值之间的关系。
	 *		如果关系是真实的，关系表达式会生成true,如果不真实生成false。
	 *		有<,>,<=,>=,==,!=。
	 *	  ·等于和不等于适用于所有的基本数据类型，而其他比较符不适合boolean类型(其他类型没有意义)。
	 *	  ·测试对象的等价性：使用==和！=可以比较对象是不是同一个对象(基本类型和所有的对象都适合)，
	 *		equals方法(不适合基本类型)比较的是两个对象的内容是不是一样的。
	 *
	 *	·逻辑运算符
	 *	  ·"&&"与 "||"或,"!"非
	 *	  ·能根据参数的逻辑关系，生成一个布尔值(true或false)
	 * 	  ·短路:当 使用"&&" 运行符时，会遇到一种"短路"现象，即一旦确定表达式的值，就不再计算下面的部分了(可以将性能提高 )。 
	 *	  ·指数记数法:在java中看见 1.39e-43f这样的表达式，它真正的含义是1.39*10的43次方
	 *	  ·三元操作符(if-else):boolean-exp ? value0 : value1  ，如果boolean-exp的值为true就计算value0，false就计算value1.  
	 *	  ·类型转换操作符:
	 *		·窄化转化:将  精度高  的转换为  精度低  的，可能会面临信息丢失，必须使用强制类型转换。例如:double d = 2.2; int i = (int)d;
	 *		·扩展转换:将  低精度  的转换为  高精度  的，不用使用强制类型转换，因为新类型肯定能容纳原来类型的信息，例如:int i = 2; double b = i;(这里不用进行强制类型转换)
	 *		·截尾和舍入:在执行窄化转换时，，如果将一个浮点数转化为整型值。将29.7转换为int，结果会是29，要想是四舍五入使用的是Math.round(29.7)，等到的就是30了
	 *
	 *	  ·提升
	 *		·对基本数据类型执行运算，只要类型比int小(即char，byte，short)，那么在运算之前，这些值会自动转换成int。这样一来，生成的结果就是int类型的
	 *		·如果想把结果赋值给较小的类型，就必须使用类型转换(可能会出现信息丢失)
	 *		·通常，表达式中出现最大的数据类型决定了表达式最终结果的数据类型。一个float与double进行运算，结果就是double(double的数据类型大，double可以容纳float，int的数据类型小)
	 *			如果将一个int和long值相加，则结果为long(long可以包含int了)
	 *		
	 *
	 *	·float 与 double 的区别 
	 *	  ·float是单精度浮点数，内存分配4个字节，占32位，有效小数位6-7位 double是双精度浮点数，内存分配8个字节，占64位，有效小数位15位 
	 *	  ·java中默认声明的小数是double类型的，例如double = 4.0 。如果float = 4.0 就会报错，需要使用下面的写法:float = 4.0f或者float x = (float)4.0
	 *		其中4.0f后面的f只是为了区别double，并没有其他的意义。
	 *	  ·对于编程人员来收，double对float的区别试试：double具有更高的精度，但是double消耗的内存是float的两倍，且double运行的效率比float慢
	 *
	 *	·整型byte,short,int,long取值范围大小 (这几个都是整数型，上面两个是小数型)
	 *	  ·byte的取值范围最小  2的7次方 -1  。(-127到127)
	 *	  ·short  2的15次方-1。（-32767到32767）
	 *	  ·int  2的31次方－1 。
	 *	  ·long  2的63次方-1。
	 *
	 */

	@Test
	public void charToInt(){
		char a = 'a';
		int b = a;
		System.out.println(b);
		char c = '1';
		int d = (int)c;
		System.out.println(d);
	}
	//前缀式与后缀式的区别
	@Test
	public void increment(){
		int i = 1;
		int a = ++i;
		System.out.println("前缀式是先进行运行，再将赋值给a，a=" + a);
		int j = 1;
		int b = j++;
		System.out.println("后缀式是先进行赋值给a，再进行运行，b=" + b);
	}
	
	
	
	//测试对象的等价性
	@Test
	public void objectEqual(){
		Integer n1 = new Integer(47);
		Integer n2 = new Integer(47);
		/*
		 * ==  运算符是用来比较是不是同一个对象的
		 * 	如果想比较两个对象的内容是否一样，那么就要使用所有对象都适用的方法equals()。但是这个方法不适合于基本类型，基本类型使用==和！=即可。
		 */
		System.out.println(n1 == n2);
		System.out.println(n1 != n2);
		//equals()方法是用来比较两个对象的内容是不是一样的
		System.out.println(n1.equals(n2));
	}
	@Test
	public void objectEqual2(){
		Value v1 = new Value();
		Value v2 = new Value();
		v1.i = v2.i = 100;
		System.out.println(v1.equals(v2));
		/*
		 * 这次令人费解，结果又是false，这是由于equals()的默认行为是比较引用，所以最好在自己的新类中覆盖equals()方法，。
		 * 	上一个之所以是true是因为，大多数的java类库都实现了equals()方法，以便用来比较对象的内容，而不是比较对象的引用
		 */
	}

	
	//注意数值过界
	@Test
	public void overFlow(){
		int big = Integer.MAX_VALUE;
		System.out.println("big:" + big);
		/*
		 * 如果两个足够大的int值执行乘法运算，结果就会溢出，等到的就不是你想要的结果了。这说明java好，但也没有那么好 
		 */
		int biggger = big*2;
		System.out.println(biggger);
	}
 	
	/*
	 * 练习
	 */
	PassObject passObject;
	Dog dog;
	ObjectG objectG;
	
	/*
	 * 第四章：控制流程
	 * 	·迭代：
	 * 	  ·while，do-while和for用来控制循环，有时将它们划分为迭代语句。语句会重复执行，直到起控制作用的布尔表达式等到"假"的结果为止。
	 * 	·java有多个关键字表示无条件分支
	 * 	  ·return用途有两个方面
	 * 	  	·指定一个方法返回的值
	 * 	  	·会导致当前方法的退出
	 * 	  ·break和continue：在任何的迭代语句的主体部分，都可以使用break和continue控制循环的流程。
	 * 		其中，break用于强行退出循环，而continue则停止执行当前的迭代(停止执行continue语句下面的代码)，然后退回循环起始处，开始下一次迭代
	 *    ·goto：java中没有goto。然而，java也能完成一些类似于跳转(goto)的方法，结合break和continue结合使用。
	 *    	标签是后面跟有冒号的标识符，就像下面这样  labell1:
	 *    	在java中，标签器作用的唯一的地方刚好是在迭代语句之前。"刚好之前"的意思表明，在标签和迭代之间置入任何语句都不好。
	 *  	而在迭代之前设置标签的唯一理由是：我们希望在其中嵌套另一个迭代或者开关。这是由于break和continue关键字通常只中断当前循环，随标签一起使用，它们就会中断循环，直到标签的地方
	 *  	例如：
	 *  		labell1：
	 *  		outer-iteration{
	 *  			inner-iteration{
 	 *  				break;//中断内部循环，跳到外部循环
	 *  				continue;//使执行点移到内部迭代的起始处
	 *  				continue labell1;//同时中断内部迭代和外部迭代，直接转到labell1处，随后，它实际上上市继续迭代的过程，从外部迭代开始
	 *  				break labell1;//同时中断内部和外部迭代，直接转到labell1处，但不重新进入迭代，实际上是中止两个迭代 
	 *  			}
	 *  		}
	 */
	public void labellWhile(){
		int i = 0;
		outer:
		while (true) {
			System.out.println("Outer while loop");
			while (true) {
				i++;
				System.out.println("i=" + i);
				if (i == 1) {
					System.out.println("continue");
					continue;//跳到内部循环的头部(即从i++开始执行)
				}
				if (i == 3) {
					System.out.println("continue outer");
					continue outer;//跳转到outer的位置。重新进入外部循环
				}
				if (i == 5) {
					System.out.println("break");
					break;//跳出内部循环，从System.out.println("Outer while loop");开始执行，
				}
				if (i == 7) {
					System.out.println("break outer");
					break outer;//调出到outer标签(即外部循环)，不再进入外部循环
				}
			}
			/*
			 *	·一般的 continue会退回最内层循环的开头(顶部)，并继续执行
			 *	·带标签的continue会到达标签的位置，并重新进入紧接在那个标签后面的循环
			 *	·一般的break会中断并跳出当前循环
			 *	·带标签的break会中断并跳出标签所指的循环，并不会重新进入循环
			 */
		}
	}
	
	
	/*
	 * switch(只能支持整数)
	 * 	·switch有时被划为一种选择语言，根据整数表达式的值，switch语句可以从一系列代码中选出一段去执行。格式如下
	 * 		 switch(integral-selector) {//integral-selector这个表达式产生的只能是整数
	 * 			case integral-value1 : statement; break;
	 * 			case integral-value1 : statement; break;
	 * 			case integral-value1 : statement; break;
	 *			//...
	 *			default: statement; 
	 * 		} 
	 * 	·其中integral-selector(只支持整数)是一个能产生整数的表达式，switch将表达式的结果与每个integral-value进行比较。相符，执行对应的语句，没有相符，执行default语句。
	 * 	  case语句中的break是可选的，如果省略break，会继续执行后面的case语句，知道遇到break为止。
	 * 	·注意：default后面不用显示配置break(显示配置也可以)，默认会break。
	 */
	
	
	
	/*
	 * 第五章：初始化与清理
	 * 	·构造器是一种特殊类型的方法，因为它没有返回值。这与返回值为空明显不同(new表达式确实返回了新建对象的引用，但构造器本身并没有返回值)
	 * 	·方法重载：方法名相同，形参不同的 方法(构造器也是方法)。
	 * 	·如果传入的参数类型 小于 声明的参数类型(精度大的参数类型就大double>float>int)，传人的参数类型就会被提升(对实际的数值是没有影响的)
	 * 		char类型略有不同，如果没法找到char类型的参数方法，就会把传人的char直接提升为int(假如你把一个char类型传人，而刚好也有接受int参数的方法)
	 * 	·如果传人的实际参数大于声明的，编译器就会出现一个错误。(例如传人的参数是double类型，但是方法要接受的是int类型的，这个时候就会报错)，必须进行类型显式配置类型转换
	 * 	·不能以返回值区分重载方法
	 */
	public void acceptDouble(double a){System.out.println(a);}
	public void acceptInt(int a){}
	@Test
	public void acceptFloat(){
		//如果一个接受一个参数的方法，可以将精度比double低的类型传进去,里面会进行自动进行类型转换(例如将int = 4传进去会转换为4.0)
		int a = 4;
		acceptDouble(a);
		//但是不接受一个比自己精度低的参数，例如将接收int参数的，将一个double类型的传进去是不能的。因为这样是有可能丢失信息的
		double b = 4;
//		acceptInt(b);//例如这样编译器就会出错
	}
	
	/*
	 * this关键字
	 * 	·this只能方法的内部使用，表示对"调用方法的那个对象"的引用。 
	 */
	
	
	/*
	 * 清理:终结处理和垃圾回收	
	 * 	·垃圾回收器只知道释放那些经过由new分配的内存。(但是有一些内存是垃圾回收器无法清理的，例如清理一个int)。
	 * 		假设你的对象(并非使用new)获得了一块"特殊"的内存区域。
	 * 		由于你的垃圾回收器只知道回收new分配的内存，所以它不知道该如何释放该对象的这块"特殊"内存。
	 * 		为了应对这种情况，java允许在类中定义一个名为finalize()的方法。它的工作原理是这样的:
	 * 		一旦垃圾回收器准备好释放对象占用的空间，将首先调用finalize()，并在下一次垃圾回收时做一些重要的工作。
	 *  ·垃圾回收只与内存有关
	 *  	使用垃圾回收器的唯一原因是为了回收程序不再使用的内存。所以对于与垃圾回收有关的任何行为来说(尤其是finalize方法)，它们也必须同内存及其回收有关。
	 *  ·finalize()的用途何在?
	 *  	是不是意味这对象中含有其他的对象(当垃圾回收器工作的时候，就没有回收到对象里面的特殊对象)，finalize()就应该明确释放那些特殊的对象?
	 *  	答案是否定的。
	 *  	无论对象是怎么创建的，垃圾回收器都会负责释放对象占据的所有内存。(所以上面的担心是多余的)
	 *  	这就对finalize()的需求限制到一种情况：即是通过某种创建对象方式  以外的方式  为  对象分配内存 的情况 。但是java中一切都是对象，这种情况是怎么回事。
	 *  	这种情况主要发生在使用"本地方法"的情况下，本地方法是一种在java中调用非java代码的方式。本地方法主支持c/c++。在非java代码中，也许会调用c的malloc函数来分配内存空间，
	 *  	而且如果没有调用free()函数，则内存就得不到释放，从而造成内存泄漏。因为free()是c/c++中函数，所以需要在finalize()中用本地方法调用它。
	 *  ·无论是"垃圾回收"还是"终结"，都不能保证一定会发生。如果java虚拟机没有面临内存耗尽的情形，它是不会浪费时间去执行垃圾回收以恢复内存。(一直监控垃圾，和垃圾回收需要大量的成本)
	 *  ·终结条件
	 *  	通常，不能指望finalize()帮你进行"清理"工作，必须创建其他的清理工作。在垃圾回收对象时，会调用一次finalize()方法(也许不会被调用)，所以可以在垃圾回收前判断一下，是否可以安全回收了。
	 *  	当你对某个对象不在感兴趣了--也就是可以被清理了，这个对象应该处于某种状态，使它占用的内存可以被安全的释放。
	 *  	例如，要是对象打开了一个文件，在垃圾回收时，程序员应该关闭这个文件。 对象是否可以安全的被回收(也就是对象存在很难看见的缺陷)。这时就可以使用finalize()来发现这种缺陷--尽管它并不是
	 *  	总被调用(只有内存耗尽才会被调用，或是手动)。
	 *  	一个例子:示范了finalize()可能的使用方式
	 */	
	public void checkIn(){
		//点击去的是一个例子，执行main方法即可
		new Book(true);
	}
	
	
	/*
	 * 垃圾回收器如何工作
	 * 	·在以前的编程语言中，在  堆  上分配对象的代价十分高昂，因此读者自然会觉得java中所有的对象(基本类型除外)都在   堆  上分配的方式也非常高昂。
	 * 	  然而，垃圾回收器对于提高对象的创建速度，却有明显的效果。听起来很奇怪--存储空间的释放竟然会影响存储空间的分配，但这确实是某些java虚拟机的工作方式。
	 * 	 这就意味着，java从  堆  分配速度，可以和其他语言从 堆栈 上分配空间的速度相媲美。
	 * 
	 * 	·可以把C++里的 堆 想象成一个院子，里面每个对象都负责 管理自己的地盘，一段时间后，对象可能被销毁，但地盘必须加以重用(这里要消耗成本)。
	 * 	  在java堆中，堆的实现是截然不同:它更像一个传送带，每分配一个新的对象，它就往前移动一格。这就意味着对象存储空间分配速度非常快 
	 *   java的 堆指针 只是简单的移动到没分配的区域，其效率比得上C++在  堆栈 上分配空间的效率。
	 *   
	 * 	·其实，java中的堆并不完全像传送带那样的工作，会导致频繁的内存页面调度，会影响性能，当创建的对象足够多的时候，最终内存耗尽。
	 * 	  其中的秘密就是不会像真的传送带那样，指针一直的往后移动，在于垃圾回收器的介入。当它工作时，将一边回收空间，一边将 堆 中的对象紧凑排列，这样指针可以更靠近传送带的开始处(就不会一直往后指)
	 * 
	 * 	·垃圾回收器的依据思想是：
	 * 	   对任何"活"的对象，一定能最终追溯到其存活在  堆栈  或 静态存储区 之中的引用。这个引用链可能会穿过数个对象层次。
	 * 	   从 堆栈 和 静态存储区 遍历所有的引用，就能找到"活"的对象。对于发现的每个引用，跟踪它引用的对象，然后找到此对象包含的所有引用(如果存在的话)。
	 * 	   如此反复进行，直到"存在于 堆 和 静态存储区 中的所有引用"所形成的网络全部被访问到为止(所有引用都能找到对象)。就是根据 堆栈 中的引用将 对应的对象全部查找出来。
	 * 	
	 * 	  在这种方式下，java采取一种 "自适应"的垃圾回收技术 。至于如何找到存活的对象，取决与java虚拟机的实现(上面也是一种方式)。
	 * 	 有一种做法名为 "停止-复制" 。显然这意味着，先把当前运行的程序暂停下来 (所以它不属于后台回收模式)，然后把活的对象(取决于java虚拟机实现，有很多种方式)从当前 堆 复制到另一个 堆 中。
	 * 	 没有被复制的全部都是垃圾。当对象被复制到新堆时，它们是一个挨着一个的，所以新堆保持紧密排列。
	 * 
	 * 	 当把对象从一处搬到另一处时，所有引用的指向必须修正。对于这种所谓的"复制式回收器"而言，效率会降低。这里有两个原因
	 * 	  ·第一个:有两个堆，然后在这两个堆之间来回倒腾，需要很多时间。
	 * 	  ·第二个问题在于复制，程序进入稳定状态之后，可能只会产生少量的垃圾，复制器仍然将所有的内存从一处复制到另一处，这要很大的成本以及浪费。
	 * 		为了避免这种情况，java虚拟机会进行检查：要是没有垃圾产生，就会转换到另一种工作模式("自适应")。这种模式称为"标记-清扫"，速度很慢，但当只产生少量垃圾时甚至没有垃圾产生，它的速度就很快了。
	 *	  ·"标记-清扫"所依据的思路同样是从  堆栈 和  静态存储区 出发，遍历所有的引用，找出与之对应的 "活"的对象，就会给这个对象一个标记，这个过程不会回收任何对象。
	 *		只有全部标记工作完成的时候，清理工作才进行，没有被标记的对象将被释放，不会发生任何的复制操作。所以剩下的  堆  是不连续的。(不像上面的"停止-复制"模式，等到的 堆 是连续的) 	
	 * 	  ·
	 * 	
	 * 	·内存分配以较大的"块"为单位，如果对象较大就会，它就会占用单独的块。严格来收，"停止-复制"要求在释放旧有对象之前，必须先把所有存活对象从 旧堆  复制到 新堆，这就导致大量的复制行为。
	 * 	  每个块都用相应的代数来记录它是否还存活。通常，如果块在某处被引用，其代数会增加。垃圾回收装置会定期进行清理动作--大型对象仍然不会被复制(只是其代数增加)，内含小型对象的那些块则被复制并整理。
	 * 	  java虚拟机进行监视，如果所有对象都很稳定，回收效率低的话，就会切换到"标记-清扫"模式，如果碎片很多就会切换到"停止-复制"模式。这就是"自适应"技术。
	 * 
	 * 	·java虚拟机中有很多附加的技术用以提升速度。尤其是与加速器操作有关，被称为"即时"编译技术。这种技术可以将程序全部或部分翻译为本地机器码(这本是java虚拟机的工作)，程序运行速度因此而提升。
	 */
	
	/*
	 *	成员初始化
	 *	  ·构造器初始化:
	 *		·初始化顺序:在构造参数调用之前，所有的变量(实例变量)都被初始化了，变量定义的先后顺序决定了初始化的顺序(不包含局部变量)，即使变量定义散步与方法定义之间，
	 *				 它们仍然会在任何方法(包含构造器)被调用之前得到初始化。
	 */
	OrderOfIn orderOfIn;//构造器初始化可以点进去看
	/*
	 * 		·静态数据的初始化(static关键字)：  无论创建多少个对象，静态数据都只占用一份存储区域。static关键字不能应用于局部变量，只能作用于域。
	 * 			 如果一个域是静态的基本类型域，且没有初始化，那就会获得基本类型的初值，如果是对象，初值是null
	 * 			静态初始化只有在必要的时刻才会进行，如果不创建该类型的对象，或者是通过类名访问，那么静态的变量就永远不会被初始化，当被初始化了，静态对象不会再次被初始化
	 * 			初始化的顺序是先"静态"后"非静态"
	 * 		
	 */
	StaticWord staticWord;//点进去
	/*
	 *  	·显式的静态初始化：静态块。也是首次创建所在的对象的时候初始化，只初始化一次
	 *  		static{
	 *  			int i = 1;
	 *  		}
	 *  	·非静态实例化初始化：看起来与  静态初始化字句  差不多，只不过是少了static关键字。这种语法对于支持"匿名内部类"的初始化时必须的。
	 *  		{
	 *  			int i = 1;
	 *  		}
	 */
	
	
	/*
	 * 可变参数列表
	 * 	·在SE5.0之前，要想使用可变参数类型是  要创建以Object数组为参数的方法，并像下面这样调用
	 * 	·注意:可变参数要放在参数列表中的最后一个
	 * 		例如:h(int a,Integer... args)
	 * 		而不能 h(Integer... args,int a)
	 */
	void printArray(Object[] args){//
		for (Object ob : args) {
			System.out.println(ob);
		}
	}
	@Test
	public void f(){
		printArray(new Object[]{1,2,3});//这里想要  可变参数  ，就将参数封装为一个数组，再将数组传入
	}
	
	/*
	 * 	·有了可变参数，就再也不用显示地编写数组语法，当你指定参数时，实际上会为你创建一个数组。你获得的仍然是一个数组
	 */
	public void varargType(){
		new VarargType();//点进去可以看可变参数的语法，以及相关的信息
		new AutoboxingVarargs();
	}
	
	/*
	 * 	·可变参数列表使得重载过程变得复杂
	 * 		在每一个中情况，编译器都会使用自动包装机制来匹配重载的方法，然后调用最明确的方法
	 */
	void h(Character... args){
		for (Character c : args) {
			System.out.print(c);
		}
		System.out.println(args.getClass());
	}
	void h(Long... args){
		for (Long i : args) {
			System.out.print(i);
		}
		System.out.println(args.getClass());
	}
	void h(Integer... args){
		for (Integer i : args) {
			System.out.print(i);
		}
		System.out.println(args.getClass());
	}
	@Test
	public void testH(){
		h('a','b');
		h(1);
//		h();
		/*
		 * 在每一次。编译器都会使用自动包装机制来匹配重载的方法，然后调用最明确匹配的方法
		 * 	但是在使用0参数调用h()时，编译器就无法知道使用的是哪一个方法了，编译器就会出现一个错误
		 */
	}
	
	

	/*
	 * 枚举类型
	 *	·在SE5.0添加了一个看似很小的特征，即关键字enum关键字(方便，安全) 
	 *	·尽管enum看起来像一种新的数据类型，但是这个关键字只是为enum生成对应的类是让编译器产生了某些编译器行为。事实上，enum确实是一个类，并且具有自己的方法。
	 */
	@Test
	public void enumType(){
		Spiciness s = Spiciness.MEDIUM;//如何定义一个枚举类型可以点进去看
		System.out.println(s.toString());
		System.out.println(s.ordinal());//编译器还会ordinal()方法，用来表示某个enum常量的声明顺序
		/*
		 * 还可以遍历进行遍历
		 */
		for (Spiciness sp : Spiciness.values()) {
			System.out.println(sp + ",ordinal" + sp.ordinal());
		}
	}
	/*
	 * enum有一个特别实用的特征，即它可以在switch语句内使用
	 * 	·由于switch是要在有限的可能值集合中进行选择，因此它与enum正是绝佳组合
	 *	·在SE5.0之前，你需要花费大量的工作才能保证与枚举等价的安全可用性。
	 */
	@Test
	public void enumType2(){
		switch (Spiciness.MEDIUM) {
		case MEDIUM:
			System.out.println("MEDIUM");
			break;
		default:
			System.out.println("default");
			break;
		}
	}
	
	
	
	/*
	 * 第六章：访问权限控制
	 * 		(注意：访问控制权限控制专注于  类库创建者  和  使用该库的外部使用者  之间的关系，这种关系也是一种通信方式)
	 * 	·包:库的单元
	 * 		当你编写一个java文件时候，此文件通常被称为编译单元。每个编译单元后面都要一个.java后缀。而编译单元后面可以有一个public类，该类的名称必须与文件名相同
	 *   	每个编译单元只能有一个public类，否则编译器就不会接受。如果编译单元还有别的类，那么它不能是public类，那么在包之外是无法看见这些类的，而且他们主要是为public类提供服务的。
	 *    
	 *   	如果想要将这些编译单元从属与同一个组群，就可以使用package。
	 *   	如果使用package，它必须是文件(除注释之外)的第一行程序代码，例如:package access;(包的命名规则全部使用小写字母)
	 *   	 会 把package名称分解为你机器目录上的一个文件夹 ，当java程序运行并且需要加载.class文件的时候，java程序就根据目录找到.class文件并加载它。
	 *   
	 *    ·java解析器的运行过程如下：
	 *   	·首先，你要设置环境变量CLASSPATH(类文件一定要放在这得子目录下面 )，例如:CLASSPATH=.;D:\JAVA\LIB;C:DOC\JavaT(环境变量可以设置多个)
	 *   	·然后可以把类文件放在 CLASSPATH中 的任何目录之下，(就是你这类文件的绝对路径前面一段一定是 CLASSPATH 中配置有的)
	 *   	·当编译器碰到mo库(假如存在这个库)的import语句时，就会在 CLASSPATH 所指定的目录中寻找，查找指定的目录中是否存在子目录(com/mo)，然后就可以选择的导入要使用的类了。
	 *   	
	 *   	·冲突：如果导入了  两个不同的库中  有名字相同 的类，就要使用指定类名:java.util.Vector v = new java.util.Vector();
	 *   
	 * 	  ·定制工具类(静态地导入包)
	 * 		·用来创建自己的工具库来减少或消除重复的程序代码。在使用这种类时，可以用一个更具有可读性的静态import语句来导入。
	 * 		  import static com.mo.Print.*;(表示可以使用Print类中的所有方法)
	 * 
	 * 	·java的访问修饰符
	 * 	  ·如果不提供修饰符，默认是包访问权限，意味着当前包中的所有其他类对那个成员都有访问权限。但对于这个包之外，所有类对这个成员杀死private。
	 * 	  ·类控制着自己成员的访问权限。
	 * 		·成员的访问修饰符为public：无论是谁，在哪里，都可以访问该成员
	 * 		·不加修饰符是赋予成员的包访问权限(同一个包内的其他类可见)，其他包的不可见
	 * 		·继承的类，可以访问父类的protected成员(private成员不行)
	 *	  ·默认包：没有给自己设定任何包的名称，java会将这样的类自动归为默认包，并给他们都赋予包访问权限(在默认包内)。
	 *
	 *	  ·private：除了本身的类，其他所有都不能访问。(就算是父类的private成员也不能访问)
	 *	  ·protected：继承访问权限，子类可以访问父类protected的成员。protected成员还具有包访问权限(相同包内的其他类可以访问protected元素)
	 *
	 *	·类的访问权限
	 *	  ·每个编译单元，只能有一个public(也可以没有，这样这个类就是包访问权限)，public类的类名要与编译单元名一样
	 *	  ·类不能是private或protected。所以对于类访问权限，仅有两个选择：包访问权限和public(什么都不写就是包访问权限)。
	 *		如果不希望其他人对该类拥有访问权限，可以将所有的构造器都指定为private，从而阻止任何人创建对象。
	 *		可以在该类的static成员内部创建对象。例如：
	 */
	Lunch Lunch;//点进去 
	
	
	/*
	 * 练习
	 */
	ConnectionManager connectionManager;//点进去看看
	
	
	
	/*
	 * 第七章：复用类
	 * 	·复用代码是java众多引人注目的功能之一
	 *	  ·第一种：(组合语法)在新类中引入现有类的代码
	 *	  ·第二种：继承语法
	 *	  ·第三种：代理
	 *
	 *	·组合语法
	 *	  ·toString():每一个非基本类型的对象都有一个toString()方法
	 *	· 继承语法
	 */
	Detergent d;//继承语法看这里
	
	/*
	 * 	·初始化基类
	 * 	  ·当你创建一个导出类对象时，该对象还包含一个基类的子对象(之所以称为基类的子对象，是因为与基本的对象还有一点区别，创建导出类时会创建基类的子对象会调用基类的构造器)，
	 * 		这个基类的子对象与你直接创建的对象是一样的，二者的区别是后者是外部的(显示写出来的)，前者是基类的子对象被包装在导出类对象的内部
	 * 	  ·对基类子对象的正确初始化：在构造器中调用基类的构造器来完成初始化
	 */
	Cartoon cartoon;//初始化一个导出类点进去看
	
	/*
	 * 	·初始化带有参数的构造器
	 */
	Chess chess;//初始化创建带有参数的导出类
	
	
	/*
	 * 	·代理
	 * 	  · java并没有提供对它的直接支持，这是继承与组合的中庸之道
	 * 	  ·将一个成员对象置于所要构造的类中(就像组合)，但是同时该类也拥有该成员对象的所有方法(与继承相似，比继承具有更大的灵活性)
	 */
	SpaceShipDelegation spaceShipDelegation;//代理类看里面
	
	
	/* ·结合使用组合和继承
	 * 	·既有组合又有代理
	 * 
	 * ·名称屏蔽
	 * 	·如果java的基类拥有某个已被多次重载的方法的名称，那么在导出类 中重载一个方法(方法名一样，参数不一样)，并不会屏蔽其在基类中的任何版本。
	 * 	    因此，无论是在该层或者它的基类中对方法进行定义，重载机制都可以正常工作的。
	 */
	Hide hide;//名称屏蔽:基类与重载剪不断的关系
	
	/*
	 * ·在组合与继承之间选择
	 * 	·组合和继承都允许在新的类中放置子对象，组合是显式地这样做，而继承则是隐式地做。
	 *	  ·组合技术通常用于想在新类中使用现有类的功能而非它的接口这种情形，一般情况下应该使域成为private
	 */
	 
	/*
	 * ·protected关键字
	 * 	·将某些事物尽可能对这个世界隐藏起来，但是允许导出类的成员去访问它们
	 * 	·访问范围：
	 * 	  ·导出类
	 * 	  ·同一个包的其他类(即包访问权限)
	 * 	·尽管可以创建protected域，但是最好的方式还是将域保持为private， 
	 */
	
	
	/*
	 *	·继承
	 *	  ·"为新类提供方法"并不是继承技术中最重要的方面，最重要的方面是用来表现新类和基类之间的关系。这种类型可以用"新类是现有类的一种类型"概括。(更多的是表现出一种层次和包含的关系)
	 *		一个例子：
	 *		·假设有一个称为Instrument的代表乐器的基类，和一个称为Wind的导出类。由于继承可以确保基类中所有的方法在导出类中也同样有效，所以能够向基类发送的所有信息同样也可以向导出类发送
	 *		如果Instrument类具有一个play()方法，那么Wind乐器也将同样具备。这意味着我们可以准确的说Wind对象也是一种类型的Instrument。
	 *	  ·向上转型：
	 *		由导出类转型成基类，在继承图是向上移动的。由于向上转型是从一个  较专用类型  向  较通用类型  转换，所以总是安全的(但是有可能出现导出类方法的丢失)。
	 *		也就是说导出类是基类的一个超集，它可能比基类含有更多的方法。但它必须至少具备基类中所含有的方法。
	 */
	Wind wind;//向上转型
	
	
	
	
	/*
	 * 	·fianl关键字 
	 * 	  ·根据上下文环境，java关键字final的含义存在着细微的区别，但通常它指的是"这是无法改变的"
	 * 	  ·不想做改变可能出于两种理由:设计或效率
	 */
	
	
	/*
	 * 	·可能使用到final的三种情况：数据，方法，类
	 * 
	 * 	  ·final数据
	 * 		·一个永不改变的编译时常量
	 * 		  ·编译器对于常量值是告知一块数据是恒定不变的，这减轻了一些运行时的负担。这类常量必须是基本数据类型，在进行定义时必须赋值。
	 * 		  ·一个既是static又是fianl的域只占据一段不能改变的存储空间
	 * 		·一个在运行时被初始化值，而你不希望它被改变
	 * 		  ·对象类型使用fianl。fianl使引用恒定不变，一旦一个fianl的 引用  被初始化指向一个对象，就无法改变该引用指向另一个对象。但是被指引的对象本身可以修改
	 */
	FinalData finalData;//final数据
	
	/*
	 * 		·空白final
	 * 		  ·java允许生成"空白final"，所谓空白final是指被声明为final但又未给定初值的域，虽然可以不用立即给定，但是编译器都确保final在使用前必须被初始化(就是在使用前被赋值)。
	 * 			(两个地方进行赋值)必须在  域的定义处  或者每个  构造器中  用表达式对final进行赋值，这正是final域在使用前总被初始化的原因所在。
	 */
	BlankFinal blankFinal;//空白final，其实并不空白
	
	/*
	 * 		·final参数
	 * 		  ·java允许在参数中声明参数为final。这意味着你无法在方法中改变参数引用所指向的对象，你可以读参数，但无法修改。
	 */
	FinalArguments finalArguments;
	
	/*	  ·final方法
	 * 		·使用final方法的原因有两个。一个是把方法锁定，以防任何继承类修改它的含义。一个是确保在继承中使方法行为保持不变，并且不会被覆盖(重写)
	 * 		  ·只有在想要明确禁止覆盖时，才将方法设置为final
	 * 		·final和private关键字
	 * 		  ·类中的所有private方法都隐式地指定为final的。由于无法取用private方法，所以也就无法覆盖它。可以对private方法添加final修饰词，但是没有任何的意义。
	 */
	FinalOverridingIllusion finalOverridingIllusion;
	
	
	/*
	 * 	  ·final类
	 * 		·当将一个类加上final(在class前面加)，就表明你不打算继承该类，而且也不允许别人这样做。
	 * 		  ·这样做是处于两种考虑，一是出于考虑，你对该类的设计永不需要任何的改动，或者出于安全的考虑，你不希望它有子类。 
	 * 		·无论类是不是被定义为final，相同的规则都适用于定义final域(就是不能被更改，参考上面final数据的规则)。
	 * 			然而，由于final类禁止继承，所以final类中的所有方法都隐式指定为final的，因为无法覆盖他们。可以给方法添加final，但是没有任何的意义。
	 */
	Jursaaic jursaaic;//final类
	
	
	/*
	 * ·初始化及类的加载
	 * 	·一般来说，"类的代码是在初次使用时才加载"，这通常加载是指发生于创建类的第一个对象时，当访问static域或者是static方法时也会被加载。
	 * 	  ·构造器也是static方法，只是没有显式的写出来。更准确的说，类是在其任何static成员被访问时才加载。
	 */

	/*
	 * ·继承与初始化
	 * 	·了解包括继承在内的初始化全过程，以对所发生的一切有个全局性的把握。
	 */
	Beetle beetle;//一个有  继承其他类  的类 是如何初始化的
	

	/*
	 * 练习
	 */
	public void test71() {new ObjectH();}
	public void test72() {new Amphibian();}
	public void test73() {new FinalAndStatic();}//final与final+static 
	public void test74() {new EmptyFinal(new Objc());}//空的final
	public void test75() {new ObjectI();}
	

	/*
	 * 第八章:多态
	 * 	·三大基本特征：数据抽象，继承，多态
	 * 	·多态的作用是：消除类型之间的耦合关系。不但能改善代码的结构组织和可读性，还可以创建可扩张的程序。 
	 * 	·多态方法调用允许一种类型表现出于其他相似类型之间的区别，只要它们都是从同一个基类中导出的。
	 */
	
	/*
	 * 	·在论向上转型
	 * 	  ·对象既可以以自己本身的类型使用，也可以作为它的基类使用
	 * 	  ·把一个对象实例用它的基类指引(基类类型作为引用)被称为向上转型。因为在继承树的画法中，基类是放置在上方的。
	 */
	Music music;
	
	/*
	 * 	·忘记对象的类型
	 * 	  ·接着上面的例子，如果让tune()方法接受一个Wind1引用会更为直观。但是这样做的话，就需要为系统内Instrument1的每一种类型都编写一个新的tune()方法
	 * 		·现在再加入Stringed和Brass这两种乐器
	 */
	Music2 music2;
	
	/*
	 * ·转机
	 * 	·看一个tune方法
	 * 		public static void tune(Instrument i) {
	 * 			i.play();
	 * 		}
	 * 	---这里接受Instrument引用的对象，但是编译器是怎么知道Instrument引用指向的是Wind1，Stringed类型还是其他类型
	 * 		因为Wind1，Stringed都有一个play方法，编译器是不知道调用的是哪一个对象的play方法。
	 * 
	 * 	·方法调用绑定
	 *	  ·将一个方法与一个方法主体(这里应该是指对象)关联起来被称为绑定
	 *		·在程序执行前进行绑定，叫前期绑定
	 *		·当编译器只有一个Instrument引用，它无法知道调用的是哪个方法。解决办法就是后期绑定
	 *		  ·后期绑定：
	 *			有一种机制，可以在运行时判断对象的类型，根据对象的类型进行绑定(其实在对象中已经安置了类型的信息，尽管已经向上转型了)，从而调用恰当的方法
	 *			也就是说，编译器一直不知道对象的类型，但是  方法调用机制  能找到正确的方法体，并加以调用。
	 *	  ·java中除了static方法和final方法(private方法也属于final)之外，其他  所有的方法  都是  后期绑定  的。这意味着通常情况下，我们不必判定是否应该进行后期绑定--它会自动发生的
	 *		所以使用final会生成更有效的代码(其实大多数整体性能不会有什么改观)。所以最好是设计来决定是否使用final，而不是性能
	 */
	
	
	/*
	 *	·产生正确的行为
	 *	  ·知道java的所有方法都是后期绑定实现多态，我们就可以编写  只与  基类  打交道的代码(划重点)
	 */
	Shapes shapes;
	
	/*
	 *	·可扩展性
	 *	  ·有了多态机制，可根据自己的需求对系统添加任意多的类型，而不用更改基类的代码，只与基类接口通信(服务端原来的代码也不用更改，因为是针对基类进行编程)。
	 *		这样的程序是可扩展的，因为可以在通用的基类继承出新的类型，增加一些新的功能。那么针对基类编程的不需要有任何的改动
	 *
	 */
	
	/*
	 * 	·缺陷：不能"覆盖"私有方法
	 * 	  ·由于private方法被自动认为是final方法，而且对导出类是屏蔽的，private方法是不能被重载的，只有非private方法才能被重载
	 */
	PrivateOverride privateOverride;
	
	/*
	 * 	·缺陷：域与静态方法不存在多态
	 * 		如果某个类是静态的，它的行为就不具有多态性，静态方法是类，而并非与单个的对象所关联
	 */
	FieldAccess fieldAccess;//域不存在多态
	StaticPolymorphism staticPolymorphism;//静态方法不存在多态
	
	/*
	 * ·构造器和多态
	 * 	·尽管 构造器不具有多态性(它们实际上是static方法，只不过该static声明是隐式的)，但还是非常有必要理解构造器怎样通过多态在复杂的层次结构中运行
	 * 	  ·对象构造过程
	 * 		1.调用基类的构造器,这个步骤会不断地反复递归下去。首先调用这种层次结构的根，然后是下一层导出类，直到最底层的导出类
	 * 		2.按声明顺序调用成员的初始化方法
	 * 		3.调用导出类的构造器
	 */
	Sandwich sandwich;//对象构造过程的调用顺序
	
	/*
	 * 	·继承与清理
	 * 	  ·通过组合和继承的方式来创建新类时，永远不用担心对象的清理问题，垃圾回收器会处理。
	 * 	  ·但是有一些情况真的需要清理的话(例如是调用c/c++之类的)，那么就要为新类创建dispose()方法。由于继承的缘故，在做清理工作时，必须在导出类中覆盖dispose()方法，
	 * 		如果导出类也存在需要清理的资源(如果你没有覆盖，基类不可你能帮你清理到的)。同时在导出类的dispose()方法中，必须调用基类的dispose()方法(使用super.dispose())
	 * 		因为导出类清理完，必须调用基类的清理工作(因为在导出类创建时，基类的对象就已经创建了，如果你不去显式的配置调用基类，那么基类的清理工作就得不到清理)
	 */
	Frog1 frog1;//继承与清理
	
	/*
	 * 	·构造器内部的多态方法的行为(基类构造器中有一个方法，导出类覆盖了，把该方法放在基类的构造器中 调用的是导出类的重写的方法，因为多态的存在)
	 * 	  ·如果调用基类的构造器，它的内部一个动态绑定方法，就要用到那个方法被覆盖之后的定义。
	 * 		然而，调用的效果是令人难以意料的，因为被覆盖的方法是在导出类对象还没有构造之前就被调用(对象都还没有创建，对象的方法就被调用了，你能想象到吗)。
	 * 
	 *	  另：·构造器只是构建对象过程的一个步骤(构建对象还有成员变量初始化..等等)。构造器只是构建对象过程的最后一个步骤，调用构造器之后对象就被创建了。
	 *	
	 *	·初始化过程
	 *	  ·在其他任何事物发生之前，将分配给对象的存储空间初始化为二进制的零
	 *	  ·调用基类的构造器。(下面例子中，调用基类构造器中调用了覆盖后的draw方法)。由于第一条，会发现值是1
	 *	  ·按照声明的顺序初始化成员变量
	 *	  ·调用导出类的构造器
	 *		·这样做有一个好处就是：那就是所有的东西都是零了。如果是对象引用则是null，这会在排除发现问题方面有很大的帮助
	 *		
	 *	·针对构造器内部的多态方法的发生，在编写构造器时有一条有效的准则
	 *	  ·用尽可能简单的方法使对象进入正常状态，避免调用其它方法
	 *	  ·构造器内唯一安全调用的方法是final方法(private也属于final)，这些方法不会被覆盖，那就不会出现多态
	 */
	PolyConstructors polyConstructors;//构造器内部的多态方法
	
	/*
	 * ·协变返回类型
	 * 	·在SE 5.0 中添加了协变返回类型，	一句话：覆盖的方法返回值类型不一定要与基类的一样(是其导出类也可以)
	 */
	CovariantReturn covariantReturn;

	/*
	 * ·向下转型与运行时类识别
	 */
	RTTI rtti;
	
	
	/*
	 * 练习
	 */
	Cycle cycle;
	Rodent rodent;
	ObjectJ objectJ;
}
