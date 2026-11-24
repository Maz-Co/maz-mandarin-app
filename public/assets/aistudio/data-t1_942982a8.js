// =========================================================
// BANK KANDUNGAN LENGKAP TINGKATAN 1 (BCK KSSM)
// 7 Bab x 7 Stesen x 5 Item = 245 Item Pembelajaran
// Nisbah: 60% Teras Buku Teks BCK T1 + 40% Pengayaan Konteks Malaysia
// =========================================================

window.DATA_T1 = {
  // =======================================================
  // BAB 1: 你好 (Apa Khabar — Sapaan, Nama & Goresan Asas)
  // =======================================================
  c1: {
    s1: [
      { emoji: '👋', hanzi: '你好', pinyin: 'nǐ hǎo', meaning: 'Apa khabar / Salam sejahtera', exCn: '哈山，你好！', exPy: 'Hāshān, nǐ hǎo!', exBm: 'Apa khabar, Hassan!' },
      { emoji: '🌅', hanzi: '早安', pinyin: 'zǎo ān', meaning: 'Selamat pagi', exCn: '老师，早安！', exPy: 'Lǎoshī, zǎo ān!', exBm: 'Selamat pagi, cikgu!' },
      { emoji: '🙏', hanzi: '谢谢', pinyin: 'xiè xie', meaning: 'Terima kasih', exCn: '谢谢你的帮忙。', exPy: 'Xièxie nǐ de bāngmáng.', exBm: 'Terima kasih atas bantuan awak.' },
      { emoji: '🙇', hanzi: '对不起', pinyin: 'duì bu qǐ', meaning: 'Maafkan saya', exCn: '对不起，我迟到了。', exPy: 'Duìbuqǐ, wǒ chídào le.', exBm: 'Maafkan saya, saya terlewat.' },
      { emoji: '🤝', hanzi: '没关系', pinyin: 'méi guān xi', meaning: 'Tidak mengapa', exCn: '没关系，请坐下。', exPy: 'Méi guānxi, qǐng zuò xià.', exBm: 'Tidak mengapa, sila duduk.' }
    ],
    s2: [
      { type: 'mcq', emoji: '🌅', instruction: 'Pilih sebutan Pinyin yang betul bagi ucapan pagi', hanzi: '早安', pinyin: 'Waktu pagi', speech: '早安', options: ['zǎo ān', 'wǔ ān', 'wǎn ān', 'nǐ hǎo'], answer: 'zǎo ān', explanation: '早安 (zǎo ān) bermaksud Selamat pagi.' },
      { type: 'mcq', emoji: '☀️', instruction: 'Apakah ucapan yang sesuai pada waktu tengah hari/petang?', hanzi: '午安', pinyin: 'wǔ ān', speech: '午安', options: ['Selamat tengah hari', 'Selamat malam', 'Jumpa lagi', 'Apa khabar'], answer: 'Selamat tengah hari', explanation: '午安 (wǔ ān) digunakan pada waktu tengah hari atau petang.' },
      { type: 'mcq', emoji: '🌙', instruction: 'Pilih sebutan tepat sebelum masuk tidur', hanzi: '晚安', pinyin: 'wǎn ān', speech: '晚安', options: ['wǎn ān', 'zǎo ān', 'wǔ ān', 'xiè xie'], answer: 'wǎn ān', explanation: '妈妈，晚安 (Māma, wǎn ān) bermaksud Selamat malam, ibu.' },
      { type: 'mcq', emoji: '❓', instruction: 'Bagaimanakah sebutan Pinyin bagi perkataan "nama"?', hanzi: '名字', pinyin: 'Kosa kata Bab 1', speech: '名字', options: ['míng zi', 'mǐng zì', 'mīng zī', 'mìng zí'], answer: 'míng zi', explanation: '名字 (míng zi) bermaksud nama.' },
      { type: 'mcq', emoji: '🏫', instruction: 'Pilih Pinyin yang betul bagi "Guru Besar / Pengetua"', hanzi: '校长', pinyin: 'Kosa kata Bab 1', speech: '校长', options: ['xiào zhǎng', 'lǎo shī', 'tóng xué', 'bān zhǎng'], answer: 'xiào zhǎng', explanation: '校长 (xiào zhǎng) bermaksud Pengetua / Guru Besar.' }
    ],
    s3: [
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan (笔顺) bagi aksara 他 (dia)?', hanzi: '他', pinyin: 'tā', speech: '他', options: ['4 goresan', '5 goresan', '6 goresan', '7 goresan'], answer: '5 goresan', explanation: 'Aksara 他 mempunyai 5 goresan (radikal 亻 + 也).' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 好 (baik)?', hanzi: '好', pinyin: 'hǎo', speech: '好', options: ['5 goresan', '6 goresan', '7 goresan', '8 goresan'], answer: '6 goresan', explanation: '好 terdiri daripada 女 (3 goresan) + 子 (3 goresan) = 6 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 我 (saya)?', hanzi: '我', pinyin: 'wǒ', speech: '我', options: ['5 goresan', '6 goresan', '7 goresan', '8 goresan'], answer: '7 goresan', explanation: 'Aksara 我 ditulis dengan 7 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 你 (awak)?', hanzi: '你', pinyin: 'nǐ', speech: '你', options: ['5 goresan', '6 goresan', '7 goresan', '8 goresan'], answer: '7 goresan', explanation: '你 mempunyai 7 goresan (radikal 亻 + 尔).' },
      { type: 'mcq', emoji: '📝', instruction: 'Apakah nama tanda baca noktah (。) dalam bahasa Cina?', hanzi: '。', pinyin: 'Tanda baca', speech: '句号', options: ['句号 (jù hào)', '逗号 (dòu hào)', '问号 (wèn hào)', '叹号 (tàn hào)'], answer: '句号 (jù hào)', explanation: 'Bulatan kecil (。) ialah 句号 (noktah) dalam penulisan bahasa Cina.' }
    ],
    s4: [
      { type: 'mcq', emoji: '😊', instruction: 'Pilih maksud perkataan ini', hanzi: '很好', pinyin: 'hěn hǎo', speech: '很好', options: ['Sangat baik', 'Sangat sibuk', 'Sangat besar', 'Sangat penat'], answer: 'Sangat baik', explanation: '很 (sangat) + 好 (baik) = Sangat baik.' },
      { type: 'mcq', emoji: '🙋', instruction: 'Pilih maksud perkataan tanya ini', hanzi: '什么', pinyin: 'shén me', speech: '什么', options: ['Apa', 'Siapa', 'Mana', 'Berapa'], answer: 'Apa', explanation: '什么 (shén me) bermaksud apa.' },
      { type: 'mcq', emoji: '👥', instruction: 'Pilih maksud perkataan ini', hanzi: '大家', pinyin: 'dà jiā', speech: '大家', options: ['Semua orang', 'Keluarga', 'Cikgu', 'Ketua kelas'], answer: 'Semua orang', explanation: '大家好 (Dàjiā hǎo) bermaksud Apa khabar semua.' },
      { type: 'mcq', emoji: '🎓', instruction: 'Pilih maksud jawatan di dalam kelas ini', hanzi: '班长', pinyin: 'bān zhǎng', speech: '班长', options: ['Ketua kelas', 'Pengetua', 'Pengawas', 'Murid baharu'], answer: 'Ketua kelas', explanation: '班长 (bān zhǎng) ialah Ketua kelas yang memberi arahan 起立，行礼.' },
      { type: 'mcq', emoji: '👋', instruction: 'Pilih maksud ucapan berpisah ini', hanzi: '再见', pinyin: 'zài jiàn', speech: '再见', options: ['Jumpa lagi', 'Selamat pagi', 'Maafkan saya', 'Sama-sama'], answer: 'Jumpa lagi', explanation: '再见 (zài jiàn) bermaksud Jumpa lagi / Selamat tinggal.' }
    ],
    s5: [
      { type: 'mcq', emoji: '💬', instruction: '美娜: 你好吗？ — Pilih jawapan tepat daripada 阿里:', hanzi: '阿里: ________，谢谢。', pinyin: 'Saya sangat baik, terima kasih', speech: '我很好，谢谢', options: ['我很好', '对不起', '再见', '我叫阿里'], answer: '我很好', explanation: 'Apabila ditanya 你好吗？, jawapannya ialah 我很好，谢谢。' },
      { type: 'mcq', emoji: '🪪', instruction: 'A: 请问你叫什么名字？ — Pilih jawapan yang lengkap:', hanzi: 'B: 我的名字____美娜。', pinyin: 'Nama saya ialah Meina', speech: '我的名字叫美娜', options: ['叫 (jiào)', '是 (shì)', '好 (hǎo)', '也 (yě)'], answer: '叫 (jiào)', explanation: '我的名字叫美娜 (Wǒ de míngzi jiào Měinà).' },
      { type: 'mcq', emoji: '🎁', instruction: '老师: 谢谢你。 — Apakah balasan sopan 小明?', hanzi: '小明: ________。', pinyin: 'Sama-sama / Tidak perlu segan', speech: '不用客气', options: ['不用客气', '没关系', '对不起', '老师早安'], answer: '不用客气', explanation: '不用客气 (bú yòng kèqi) ialah balasan sopan untuk terima kasih.' },
      { type: 'mcq', emoji: '💥', instruction: '艾伦 terlanggar buku 大卫 lalu berkata “对不起”. Apa kata 大卫?', hanzi: '大卫: ________。', pinyin: 'Tidak mengapa', speech: '没关系', options: ['没关系', '不用客气', '谢谢你', '晚安'], answer: '没关系', explanation: '对不起 (Maaf) dibalas dengan 没关系 (Tidak mengapa).' },
      { type: 'mcq', emoji: '🏫', instruction: 'Apabila guru masuk ke kelas pada waktu petang, 班长 memberi arahan:', hanzi: '起立，行礼。老师，____！', pinyin: 'Selamat petang/tengah hari, cikgu!', speech: '老师午安', options: ['午安', '晚安', '早安', '再见'], answer: '午安', explanation: 'Pada sesi tengah hari/petang, murid mengucapkan 老师，午安！' }
    ],
    s6: [
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Nama saya Hassan', speech: '我叫哈山', words: ['哈山', '我', '叫'], answerOrder: ['我', '叫', '哈山'], explanation: '我 (Saya) + 叫 (dipanggil) + 哈山 (Hassan).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Saya juga sangat baik', speech: '我也很好', words: ['很好', '也', '我'], answerOrder: ['我', '也', '很好'], explanation: 'Kata keterangan 也 (juga) diletakkan selepas subjek 我.' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Tumpang tanya, siapa nama awak?', speech: '请问你叫什么名字', words: ['什么名字', '请问', '你叫'], answerOrder: ['请问', '你叫', '什么名字'], explanation: '请问 (Tumpang tanya) diletakkan di permulaan ayat untuk kesopanan.' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Selamat malam, ibu', speech: '妈妈晚安', words: ['晚安', '妈妈'], answerOrder: ['妈妈', '晚安'], explanation: 'Panggilan 妈妈 diletakkan di hadapan ucapan 晚安.' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Apa khabar semua, terima kasih', speech: '大家好谢谢', words: ['谢谢', '大家好'], answerOrder: ['大家好', '谢谢'], explanation: 'Ucapan sapaan 大家好 diikuti ucapan terima kasih 谢谢.' }
    ],
    s7: [
      { type: 'comprehension', instruction: 'Baca dialog dan jawab soalan', passage: '美娜：你好！请问你叫什么名字？\n哈山：你好！我叫哈山，你呢？\n美娜：我的名字叫美娜。', speech: '美娜：你好！请问你叫什么名字？哈山：你好！我叫哈山。', question: 'Siapakah nama murid lelaki dalam dialog ini?', options: ['Hassan (哈山)', 'Meina (美娜)', 'Raju (拉祖)', 'Siti (西蒂)'], answer: 'Hassan (哈山)', explanation: '哈山 berkata: 我叫哈山 (Nama saya Hassan).' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '早上七点，拉祖在学校门口看见王老师。\n拉祖说：“老师，早安！”', speech: '早上七点，拉祖在学校门口看见王老师。拉祖说：老师，早安！', question: 'Apakah ucapan Raju kepada Cikgu Wang pada pukul 7 pagi?', options: ['老师，早安！', '老师，午安！', '老师，晚安！', '老师，再见！'], answer: '老师，早安！', explanation: 'Pada waktu pagi (早上), ucapan yang betul ialah 老师，早安！' },
      { type: 'comprehension', instruction: 'Baca dialog dan jawab soalan', passage: '拉祖：西蒂，晚上好！\n西蒂：拉祖，晚上好！你好吗？\n拉祖：我很好，谢谢。', speech: '拉祖：西蒂，晚上好！西蒂：拉祖，晚上好！', question: 'Bilakah perbualan antara Raju dan Siti ini berlaku?', options: ['Waktu malam', 'Waktu pagi', 'Waktu tengah hari', 'Waktu subuh'], answer: 'Waktu malam', explanation: '晚上好 (wǎnshang hǎo) digunakan pada waktu senja atau malam.' },
      { type: 'comprehension', instruction: 'Baca situasi dan jawab soalan', passage: '放学了，小明回家之前对老师说：\n“老师，再见！”', speech: '放学了，小明回家之前对老师说：老师，再见！', question: 'Bilakah Xiao Ming mengucapkan “老师，再见！”?', options: ['Sebelum pulang ke rumah selepas tamat sekolah', 'Ketika bangun tidur', 'Semasa meminjam buku', 'Ketika meminta maaf'], answer: 'Sebelum pulang ke rumah selepas tamat sekolah', explanation: '放学了，回家之前 bermaksud tamat sekolah, sebelum pulang ke rumah.' },
      { type: 'comprehension', instruction: 'Baca dialog dan jawab soalan', passage: '艾伦：对不起，我迟到了。\n老师：没关系，请进来坐下。', speech: '艾伦：对不起，我迟到了。老师：没关系。', question: 'Mengapakah Alan meminta maaf kepada guru?', options: ['Kerana dia datang lewat', 'Kerana dia lupa membawa buku', 'Kerana dia tidak sihat', 'Kerana dia tertumpahkan air'], answer: 'Kerana dia datang lewat', explanation: '我迟到了 (wǒ chídào le) bermaksud saya datang lewat.' }
    ]
  },

  // =======================================================
  // BAB 2: 我的生日 (Hari Lahir Saya — Nombor, Tarikh & Masa)
  // =======================================================
  c2: {
    s1: [
      { emoji: '🎂', hanzi: '生日快乐', pinyin: 'shēng rì kuài lè', meaning: 'Selamat Hari Lahir', exCn: '丽莎，祝你生日快乐！', exPy: 'Lìshā, zhù nǐ shēng rì kuài lè!', exBm: 'Lisa, selamat hari lahir untuk kamu!' },
      { emoji: '🎁', hanzi: '礼物', pinyin: 'lǐ wù', meaning: 'Hadiah', exCn: '爸爸送我一份生日礼物。', exPy: 'Bàba sòng wǒ yī fèn shēngrì lǐwù.', exBm: 'Ayah menghadiahkan saya satu hadiah hari lahir.' },
      { emoji: '🍰', hanzi: '蛋糕', pinyin: 'dàn gāo', meaning: 'Kek', exCn: '桌子上有生日蛋糕。', exPy: 'Zhuōzi shang yǒu shēngrì dàngāo.', exBm: 'Di atas meja ada kek hari lahir.' },
      { emoji: '📅', hanzi: '星期五', pinyin: 'xīng qī wǔ', meaning: 'Hari Jumaat', exCn: '今天是星期五。', exPy: 'Jīntiān shì xīngqīwǔ.', exBm: 'Hari ini ialah hari Jumaat.' },
      { emoji: '⏰', hanzi: '七点半', pinyin: 'qī diǎn bàn', meaning: 'Pukul 7:30', exCn: '现在是早上七点半。', exPy: 'Xiànzài shì zǎoshang qī diǎn bàn.', exBm: 'Sekarang pukul 7:30 pagi.' }
    ],
    s2: [
      { type: 'mcq', emoji: '🔢', instruction: 'Pilih bacaan yang tepat bagi nombor 36', hanzi: '36', pinyin: 'Nombor dua digit', speech: '三十六', options: ['三十六 (sān shí liù)', '六十三 (liù shí sān)', '三百六 (sān bǎi liù)', '十三六 (shí sān liù)'], answer: '三十六 (sān shí liù)', explanation: '36 dibaca 三十六 (sān shí liù).' },
      { type: 'mcq', emoji: '💯', instruction: 'Bagaimanakah nombor 108 dibaca dalam bahasa Cina?', hanzi: '108', pinyin: 'Peraturan "零" (líng)', speech: '一百零八', options: ['一百零八 (yī bǎi líng bā)', '一百八 (yī bǎi bā)', '一零八 (yī líng bā)', '十八 (shí bā)'], answer: '一百零八 (yī bǎi líng bā)', explanation: 'Apabila nilai tempat puluh ialah sifar, ia dibaca 零 (líng): 一百零八.' },
      { type: 'mcq', emoji: '📆', instruction: 'Apakah bacaan tahun kelahiran "2007年"?', hanzi: '2007年', pinyin: 'Cara baca tahun', speech: '二零零七年', options: ['二〇〇七年 (èr líng líng qī nián)', '两千零七年', '二十七年', '二百零七年'], answer: '二〇〇七年 (èr líng líng qī nián)', explanation: 'Tahun dibaca digit demi digit: 二〇〇七年.' },
      { type: 'mcq', emoji: '🎈', instruction: 'Pilih sebutan Pinyin yang betul bagi "umur / tahun"', hanzi: '十三岁', pinyin: 'Umur 13 tahun', speech: '十三岁', options: ['shí sān suì', 'shí sān shuì', 'sì sān suì', 'shí shān suì'], answer: 'shí sān suì', explanation: '岁 (suì) bermaksud tahun (umur).' },
      { type: 'mcq', emoji: '🎉', instruction: 'Pilih sebutan yang tepat bagi perkataan "meraikan"', hanzi: '庆祝', pinyin: 'Kosa kata Bab 2', speech: '庆祝', options: ['qìng zhù', 'qīng zhǔ', 'jìng zhù', 'qǐng zhù'], answer: 'qìng zhù', explanation: '庆祝 (qìng zhù) bermaksud meraikan atau menyambut.' }
    ],
    s3: [
      { type: 'mcq', emoji: '☀️', instruction: 'Aksara 日 (rì) berasal daripada lukisan purba...', hanzi: '日', pinyin: 'Evolusi Hanzi', speech: '日', options: ['Matahari', 'Bulan sabit', 'Gunung', 'Sungai'], answer: 'Matahari', explanation: '日 (rì) pada asalnya melambangkan matahari.' },
      { type: 'mcq', emoji: '🌙', instruction: 'Aksara 月 (yuè) melambangkan bentuk...', hanzi: '月', pinyin: 'Evolusi Hanzi', speech: '月', options: ['Bulan sabit', 'Bintang', 'Awan', 'Pokok'], answer: 'Bulan sabit', explanation: '月 (yuè) berbentuk bulan sabit.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 年 (tahun)?', hanzi: '年', pinyin: 'nián', speech: '年', options: ['5 goresan', '6 goresan', '7 goresan', '8 goresan'], answer: '6 goresan', explanation: 'Aksara 年 mempunyai 6 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 是 (ialah)?', hanzi: '是', pinyin: 'shì', speech: '是', options: ['7 goresan', '8 goresan', '9 goresan', '10 goresan'], answer: '9 goresan', explanation: 'Aksara 是 terdiri daripada 日 (4) + 疋 (5) = 9 goresan.' },
      { type: 'mcq', emoji: '🐯', instruction: 'Dalam cerita Bab 2, haiwan manakah yang menyambut hari lahir?', hanzi: '小老虎', pinyin: 'xiǎo lǎo hǔ', speech: '小老虎', options: ['Anak harimau', 'Anak kucing', 'Anak lembu', 'Monyet'], answer: 'Anak harimau', explanation: '今天小老虎庆祝生日 bermaksud Hari ini anak harimau menyambut hari lahir.' }
    ],
    s4: [
      { type: 'mcq', emoji: '🎂', instruction: 'Pilih maksud perkataan ini', hanzi: '出生', pinyin: 'chū shēng', speech: '出生', options: ['Dilahirkan', 'Bersekolah', 'Bekerja', 'Melancong'], answer: 'Dilahirkan', explanation: '出生 (chū shēng) bermaksud dilahirkan.' },
      { type: 'mcq', emoji: '⏪', instruction: 'Pilih maksud perkataan masa ini', hanzi: '昨天', pinyin: 'zuó tiān', speech: '昨天', options: ['Semalam', 'Hari ini', 'Esok', 'Lusa'], answer: 'Semalam', explanation: '昨天 (zuó tiān) = Semalam; 今天 = Hari ini; 明天 = Esok.' },
      { type: 'mcq', emoji: '⏩', instruction: 'Pilih maksud perkataan masa ini', hanzi: '明天', pinyin: 'míng tiān', speech: '明天', options: ['Esok', 'Semalam', 'Tahun lepas', 'Bulan depan'], answer: 'Esok', explanation: '明天 (míng tiān) bermaksud esok.' },
      { type: 'mcq', emoji: '😄', instruction: 'Pilih maksud perasaan ini', hanzi: '开心', pinyin: 'kāi xīn', speech: '开心', options: ['Gembira / Seronok', 'Sedih', 'Marah', 'Takut'], answer: 'Gembira / Seronok', explanation: '我很开心 (Wǒ hěn kāixīn) bermaksud Saya sangat gembira.' },
      { type: 'mcq', emoji: '🇲🇾', instruction: 'Tarikh 31.08.1957 dalam bahasa Cina ialah...', hanzi: '八月三十一日', pinyin: 'Hari Kebangsaan Malaysia', speech: '八月三十一日', options: ['31 Ogos', '31 Julai', '8 Mac', '13 Ogos'], answer: '31 Ogos', explanation: 'Dalam format tarikh Cina, Bulan (八月) disebut dahulu sebelum Hari (三十一日).' }
    ],
    s5: [
      { type: 'mcq', emoji: '📅', instruction: '老师: 今天是星期几？ — Pilih jawapan yang tepat:', hanzi: '学生: 今天是________。', pinyin: 'Hari ini hari Isnin', speech: '今天是星期一', options: ['星期一', '一月', '一点', '一岁'], answer: '星期一', explanation: '星期几 bertanya tentang hari dalam seminggu; jawapannya 星期一 (Isnin).' },
      { type: 'mcq', emoji: '🎂', instruction: 'A: 你的生日是几月几日？ — Pilih susunan tarikh Cina yang betul:', hanzi: 'B: ________是我的生日。', pinyin: '25 Jun ialah hari lahir saya', speech: '六月二十五日是我的生日', options: ['六月二十五日', '二十五日六月', '六日二十五月', '星期六二十五'], answer: '六月二十五日', explanation: 'Susunan tarikh Mandarin: Tahun -> Bulan (六月) -> Hari (二十五日).' },
      { type: 'mcq', emoji: '⌚', instruction: 'A: 请问现在几点了？ (Jam 12:30 tengah hari) — Pilih jawapan tepat:', hanzi: 'B: 现在是中午________。', pinyin: 'Pukul 12:30 tengah hari', speech: '现在是中午十二点半', options: ['十二点半', '十二点分', '半十二点', '十二半'], answer: '十二点半', explanation: '30 minit disebut 半 (bàn): 十二点半.' },
      { type: 'mcq', emoji: '🎁', instruction: 'Apabila kawan menyambut hari lahir, apakah ucapan kita?', hanzi: '祝你________！', pinyin: 'Selamat Hari Lahir!', speech: '祝你生日快乐', options: ['生日快乐', '早上好', '没关系', '不用客气'], answer: '生日快乐', explanation: '祝你生日快乐 (Zhù nǐ shēngrì kuàilè) bermaksud Selamat Hari Lahir.' },
      { type: 'mcq', emoji: '🕒', instruction: 'Jam menunjukkan 3:35 petang. Bagaimanakah cara menyebutnya?', hanzi: '下午三点________。', pinyin: '35 minit', speech: '下午三点三十五分', options: ['三十五分', '三十五半', '三十五点', '三十五日'], answer: '三十五分', explanation: 'Minit dinyatakan dengan 分 (fēn): 三点三十五分.' }
    ],
    s6: [
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Umur saya 13 tahun tahun ini', speech: '我今年十三岁', words: ['十三岁', '今年', '我'], answerOrder: ['我', '今年', '十三岁'], explanation: 'Subjek 我 + Masa 今年 + Umur 十三岁.' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Ayah menghadiahkan saya kek', speech: '爸爸送我蛋糕', words: ['蛋糕', '送我', '爸爸'], answerOrder: ['爸爸', '送我', '蛋糕'], explanation: '爸爸 (Ayah) + 送我 (menghadiahkan saya) + 蛋糕 (kek).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Hari ini ialah hari Jumaat', speech: '今天是星期五', words: ['星期五', '今天', '是'], answerOrder: ['今天', '是', '星期五'], explanation: '今天 (Hari ini) + 是 (ialah) + 星期五 (Jumaat).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Sekarang pukul 7 pagi', speech: '现在是早上七点', words: ['早上七点', '现在', '是'], answerOrder: ['现在', '是', '早上七点'], explanation: '现在 (Sekarang) + 是 (ialah) + 早上七点 (7 pagi).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Keluarga meraikan hari lahir untuk saya', speech: '家人为我庆祝生日', words: ['庆祝生日', '家人', '为我'], answerOrder: ['家人', '为我', '庆祝生日'], explanation: '家人 (Ahli keluarga) + 为我 (untuk saya) + 庆祝生日 (meraikan hari lahir).' }
    ],
    s7: [
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '大家好，我是哈山。我今年十三岁。我是二〇〇七年出生的。六月二十五日是我的生日。', speech: '大家好，我是哈山。我今年十三岁。六月二十五日是我的生日。', question: 'Bilakah tarikh lahir Hassan?', options: ['25 Jun', '19 Ogos', '28 Februari', '31 Ogos'], answer: '25 Jun', explanation: '六月二十五日 bermaksud 25 Jun.' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '八月十九日是我的生日。家人为我庆祝生日。爸爸送我生日蛋糕和礼物。我很开心。', speech: '八月十九日是我的生日。爸爸送我生日蛋糕和礼物。', question: 'Apakah yang dihadiahkan oleh ayah kepada Lisa?', options: ['Kek hari lahir dan hadiah', 'Buku dan pensel', 'Susu dan roti', 'Jam tangan'], answer: 'Kek hari lahir dan hadiah', explanation: '爸爸送我生日蛋糕和礼物 bermaksud ayah menghadiahkan kek dan hadiah.' },
      { type: 'comprehension', instruction: 'Baca jadual dan jawab soalan', passage: '今天是星期三，五月十日。昨天是星期二，明天是星期四。', speech: '今天是星期三，五月十日。明天是星期四。', question: 'Jika hari ini 10 Mei (Rabu), bilakah hari Khamis?', options: ['11 Mei (Esok)', '9 Mei (Semalam)', '12 Mei', '8 Mei'], answer: '11 Mei (Esok)', explanation: '明天是星期四 bermaksud esok ialah hari Khamis (11 Mei).' },
      { type: 'comprehension', instruction: 'Baca cerita dan jawab soalan', passage: '今天小老虎庆祝生日。小狗、小猫、猴子、小鸟、小牛和小鸡都来参加小老虎的生日会。', speech: '小狗、小猫、猴子、小鸟、小牛和小鸡都来参加小老虎的生日会。', question: 'Berapa jenis haiwan yang hadir ke majlis hari lahir anak harimau?', options: ['6 jenis haiwan', '4 jenis haiwan', '5 jenis haiwan', '7 jenis haiwan'], answer: '6 jenis haiwan', explanation: 'Terdapat 6 haiwan: 小狗, 小猫, 猴子, 小鸟, 小牛, dan 小鸡.' },
      { type: 'comprehension', instruction: 'Baca dialog masa dan jawab soalan', passage: 'Aiman: 现在几点了？\nSiti: 现在是晚上八点二十分。我们要温习功课了。', speech: '现在是晚上八点二十分。', question: 'Pukul berapakah Aiman dan Siti mengulang kaji pelajaran?', options: ['8:20 malam', '8:20 pagi', '2:08 petang', '7:20 malam'], answer: '8:20 malam', explanation: '晚上八点二十分 bermaksud 8:20 malam.' }
    ]
  },

  // =======================================================
  // BAB 3: 我的身体 (Badan Saya — Anggota Badan & Pancaindera)
  // =======================================================
  c3: {
    s1: [
      { emoji: '👀', hanzi: '眼睛', pinyin: 'yǎn jing', meaning: 'Mata', exCn: '我有两只大眼睛。', exPy: 'Wǒ yǒu liǎng zhī dà yǎnjing.', exBm: 'Saya mempunyai sepasang mata yang besar.' },
      { emoji: '👂', hanzi: '耳朵', pinyin: 'ěr duo', meaning: 'Telinga', exCn: '我们用耳朵听音乐。', exPy: 'Wǒmen yòng ěrduo tīng yīnyuè.', exBm: 'Kita menggunakan telinga untuk mendengar muzik.' },
      { emoji: '👃', hanzi: '鼻子', pinyin: 'bí zi', meaning: 'Hidung', exCn: '这是我的鼻子。', exPy: 'Zhè shì wǒ de bízi.', exBm: 'Ini ialah hidung saya.' },
      { emoji: '✋', hanzi: '手', pinyin: 'shǒu', meaning: 'Tangan', exCn: '饭前要洗手。', exPy: 'Fàn qián yào xǐ shǒu.', exBm: 'Sebelum makan mesti mencuci tangan.' },
      { emoji: '🦶', hanzi: '脚', pinyin: 'jiǎo', meaning: 'Kaki', exCn: '我们用脚踢足球。', exPy: 'Wǒmen yòng jiǎo tī zúqiú.', exBm: 'Kita menggunakan kaki untuk bermain bola sepak.' }
    ],
    s2: [
      { type: 'mcq', emoji: '💇', instruction: 'Pilih sebutan Pinyin yang betul bagi "rambut"', hanzi: '头发', pinyin: 'Kosa kata Bab 3', speech: '头发', options: ['tóu fa', 'tóu fà', 'tǒu fā', 'tòu fá'], answer: 'tóu fa', explanation: '头发 dibaca tóu fa (suku kata kedua bernada ringan).' },
      { type: 'mcq', emoji: '💪', instruction: 'Bagaimanakah sebutan bagi anggota "bahu"?', hanzi: '肩膀', pinyin: 'Kosa kata Bab 3', speech: '肩膀', options: ['jiān bǎng', 'jiǎn bāng', 'qiān bǎng', 'jiàn páng'], answer: 'jiān bǎng', explanation: '肩膀 (jiān bǎng) bermaksud bahu.' },
      { type: 'mcq', emoji: '🦷', instruction: 'Pilih sebutan yang tepat bagi "gigi"', hanzi: '牙齿', pinyin: 'Kosa kata Bab 3', speech: '牙齿', options: ['yá chǐ', 'yǎ chí', 'yā chī', 'yà chì'], answer: 'yá chǐ', explanation: '牙齿 (yá chǐ) bermaksud gigi.' },
      { type: 'mcq', emoji: '👄', instruction: 'Pilih Pinyin yang betul bagi "bibir"', hanzi: '嘴唇', pinyin: 'Kosa kata Bab 3', speech: '嘴唇', options: ['zuǐ chún', 'zhuǐ cún', 'zuī chūn', 'zǔi chǔn'], answer: 'zuǐ chún', explanation: '嘴唇 (zuǐ chún) bermaksud bibir.' },
      { type: 'mcq', emoji: '🦵', instruction: 'Apakah sebutan bagi "lutut"?', hanzi: '膝', pinyin: 'Kosa kata Bab 3', speech: '膝', options: ['xī', 'qī', 'xǐ', 'sī'], answer: 'xī', explanation: '膝 (xī) bermaksud lutut.' }
    ],
    s3: [
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 头 (kepala)?', hanzi: '头', pinyin: 'tóu', speech: '头', options: ['4 goresan', '5 goresan', '6 goresan', '7 goresan'], answer: '5 goresan', explanation: 'Aksara 头 mempunyai 5 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 身 (badan)?', hanzi: '身', pinyin: 'shēn', speech: '身', options: ['6 goresan', '7 goresan', '8 goresan', '9 goresan'], answer: '7 goresan', explanation: 'Aksara 身 ditulis dengan 7 goresan.' },
      { type: 'mcq', emoji: '🧩', instruction: 'Apakah radikal di sebelah kiri aksara 脸 (muka) dan 脚 (kaki)?', hanzi: '月', pinyin: 'yuèzìpáng', speech: '脚', options: ['Radikal anggota badan (月)', 'Radikal air (氵)', 'Radikal kayu (木)', 'Radikal api (火)'], answer: 'Radikal anggota badan (月)', explanation: 'Banyak nama anggota badan seperti 脸, 脚, dan 膝 mempunyai radikal 月 (肉).' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 脸 (muka)?', hanzi: '脸', pinyin: 'liǎn', speech: '脸', options: ['9 goresan', '10 goresan', '11 goresan', '12 goresan'], answer: '11 goresan', explanation: 'Aksara 脸 mempunyai 11 goresan.' },
      { type: 'mcq', emoji: '👁️', instruction: 'Radikal 目 di sebelah kiri aksara 眼 (mata) bermaksud...', hanzi: '目', pinyin: 'mùzìpáng', speech: '眼睛', options: ['Mata / Penglihatan', 'Telinga', 'Mulut', 'Tangan'], answer: 'Mata / Penglihatan', explanation: '目 (mù) ialah radikal yang berkaitan dengan mata.' }
    ],
    s4: [
      { type: 'mcq', emoji: '😊', instruction: 'Pilih maksud anggota badan ini', hanzi: '脸', pinyin: 'liǎn', speech: '脸', options: ['Muka / Wajah', 'Leher', 'Perut', 'Belakang'], answer: 'Muka / Wajah', explanation: '脸 (liǎn) bermaksud muka.' },
      { type: 'mcq', emoji: '🖐️', instruction: 'Pilih maksud perkataan ini', hanzi: '手指', pinyin: 'shǒu zhǐ', speech: '手指', options: ['Jari tangan', 'Jari kaki', 'Tapak tangan', 'Kuku'], answer: 'Jari tangan', explanation: '手指 (shǒu zhǐ) = jari tangan; 脚趾 (jiǎo zhǐ) = jari kaki.' },
      { type: 'mcq', emoji: '👅', instruction: 'Pilih maksud anggota di dalam mulut ini', hanzi: '舌头', pinyin: 'shé tou', speech: '舌头', options: ['Lidah', 'Gigi', 'Bibir', 'Pipi'], answer: 'Lidah', explanation: '舌头 (shé tou) bermaksud lidah.' },
      { type: 'mcq', emoji: '🤨', instruction: 'Pilih maksud bahagian di atas mata ini', hanzi: '眉毛', pinyin: 'méi mao', speech: '眉毛', options: ['Kening', 'Bulu mata', 'Dahi', 'Dagu'], answer: 'Kening', explanation: '眉毛 (méi mao) bermaksud kening.' },
      { type: 'mcq', emoji: '🏃', instruction: 'Pilih maksud keseluruhan perkataan ini', hanzi: '身体', pinyin: 'shēn tǐ', speech: '身体', options: ['Badan / Tubuh', 'Kesihatan', 'Sukan', 'Pakaian'], answer: 'Badan / Tubuh', explanation: '身体 (shēn tǐ) bermaksud badan atau tubuh.' }
    ],
    s5: [
      { type: 'mcq', emoji: '👉', instruction: 'Guru menunjuk ke arah hidung: 这是我的____。', hanzi: '这是我的____。', pinyin: 'Ini ialah hidung saya', speech: '这是我的鼻子', options: ['鼻子', '耳朵', '脚趾', '肩膀'], answer: '鼻子', explanation: '这是我的鼻子 (Zhè shì wǒ de bízi) bermaksud Ini ialah hidung saya.' },
      { type: 'mcq', emoji: '🧩', instruction: 'Teka-teki: “左一片，右一片，没有它，听不见。” Apakah ia?', hanzi: '听不见 (Tidak dapat mendengar)', pinyin: 'Teka-teki pancaindera', speech: '耳朵', options: ['耳朵 (Telinga)', '眼睛 (Mata)', '鼻子 (Hidung)', '嘴巴 (Mulut)'], answer: '耳朵 (Telinga)', explanation: '听不见 bermaksud tidak dapat mendengar — jawapannya ialah 耳朵 (telinga).' },
      { type: 'mcq', emoji: '👃', instruction: 'Teka-teki: “是香还是臭，问它它都懂。” Apakah ia?', hanzi: '香还是臭 (Wangi atau busuk)', pinyin: 'Deria bau', speech: '鼻子', options: ['鼻子 (Hidung)', '眼睛 (Mata)', '耳朵 (Telinga)', '手掌 (Tapak tangan)'], answer: '鼻子 (Hidung)', explanation: 'Hidung (鼻子) menghidu bau wangi (香) atau busuk (臭).' },
      { type: 'mcq', emoji: '🍇', instruction: 'Teka-teki: “两颗葡萄黑又亮，白天陪我看世界。” Apakah ia?', hanzi: '看世界 (Melihat dunia)', pinyin: 'Deria penglihatan', speech: '眼睛', options: ['眼睛 (Mata)', '嘴巴 (Mulut)', '鼻子 (Hidung)', '眉毛 (Kening)'], answer: '眼睛 (Mata)', explanation: 'Mata (眼睛) diibaratkan seperti dua biji anggur hitam berkilau yang melihat dunia.' },
      { type: 'mcq', emoji: '👍', instruction: 'Dalam bahasa Cina, jari ibu (ibu jari) dipanggil...', hanzi: '____指', pinyin: 'Nama jari', speech: '拇指', options: ['拇指 (mǔ zhǐ)', '食指 (shí zhǐ)', '中指 (zhōng zhǐ)', '小指 (xiǎo zhǐ)'], answer: '拇指 (mǔ zhǐ)', explanation: '拇指 ialah ibu jari; 食指 (telunjuk); 中指 (hantu); 无名指 (manis); 小指 (kelingking).' }
    ],
    s6: [
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Ini ialah mata saya', speech: '这是我的眼睛', words: ['眼睛', '这是', '我的'], answerOrder: ['这是', '我的', '眼睛'], explanation: '这是 (Ini ialah) + 我的 (saya punya) + 眼睛 (mata).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Itu ialah tangan Aiman', speech: '那是艾曼的手', words: ['的手', '那是', '艾曼'], answerOrder: ['那是', '艾曼', '的手'], explanation: '那是 (Itu ialah) + 艾曼的手 (tangan Aiman).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Rambut kakak sangat panjang', speech: '姐姐的头发很长', words: ['很长', '头发', '姐姐的'], answerOrder: ['姐姐的', '头发', '很长'], explanation: '姐姐的头发 (Rambut kakak) + 很长 (sangat panjang).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Kita mempunyai dua belah kaki', speech: '我们有两只脚', words: ['两只脚', '我们', '有'], answerOrder: ['我们', '有', '两只脚'], explanation: '我们 (Kita) + 有 (mempunyai) + 两只脚 (dua belah kaki).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Gigi adik sangat putih', speech: '弟弟的牙齿很白', words: ['很白', '牙齿', '弟弟的'], answerOrder: ['弟弟的', '牙齿', '很白'], explanation: '弟弟的牙齿 (Gigi adik) + 很白 (sangat putih).' }
    ],
    s7: [
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '我叫艾曼。我有大大的眼睛、高高的鼻子和黑黑的头发。我每天早上刷牙齿。', speech: '我叫艾曼。我有大大的眼睛、高高的鼻子和黑黑的头发。', question: 'Apakah warna rambut Aiman dalam petikan ini?', options: ['Hitam (黑黑的)', 'Perang', 'Putih', 'Kelabu'], answer: 'Hitam (黑黑的)', explanation: '黑黑的头发 bermaksud rambut yang hitam.' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '体育课时，同学们在草场上做运动。大家举起双手，再摸一摸膝盖和脚趾。', speech: '大家举起双手，再摸一摸膝盖和脚趾。', question: 'Di manakah murid-murid melakukan senaman Pendidikan Jasmani?', options: ['Di padang (草场)', 'Di perpustakaan', 'Di kantin', 'Di makmal'], answer: 'Di padang (草场)', explanation: '在草场上 bermaksud di atas padang.' },
      { type: 'comprehension', instruction: 'Baca teka-teki dan jawab soalan', passage: '红红两片，可开可关。吃饭说话，都靠它帮忙。', speech: '红红两片，可开可关。', question: 'Apakah anggota badan yang dimaksudkan dalam teka-teki ini?', options: ['Mulut / Bibir (嘴巴)', 'Telinga (耳朵)', 'Mata (眼睛)', 'Kaki (脚)'], answer: 'Mulut / Bibir (嘴巴)', explanation: 'Mulut (嘴巴) berwarna merah dan boleh dibuka serta ditutup untuk makan dan bercakap.' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '小美用眼睛看书，用耳朵听老师讲课，用手写汉字。', speech: '小美用眼睛看书，用耳朵听老师讲课，用手写汉字。', question: 'Apakah anggota badan yang digunakan oleh Xiao Mei untuk menulis aksara Cina?', options: ['Tangan (手)', 'Kaki (脚)', 'Hidung (鼻子)', 'Lutut (膝)'], answer: 'Tangan (手)', explanation: '用手写汉字 bermaksud menggunakan tangan untuk menulis aksara Cina.' },
      { type: 'comprehension', instruction: 'Baca dialog dan jawab soalan', passage: '哈山：你的手怎么了？\n拉祖：刚才打篮球时，我的手指有点痛。', speech: '刚才打篮球时，我的手指有点痛。', question: 'Bahagian manakah pada tangan Raju yang berasa sakit?', options: ['Jari tangan (手指)', 'Tapak tangan (手掌)', 'Belakang tangan (手背)', 'Bahu (肩膀)'], answer: 'Jari tangan (手指)', explanation: '我的手指有点痛 bermaksud jari tangan saya berasa sedikit sakit.' }
    ]
  },

  // =======================================================
  // BAB 4: 我的家人 (Keluarga Saya — Panggilan & Salasilah)
  // =======================================================
  c4: {
    s1: [
      { emoji: '👴', hanzi: '爷爷', pinyin: 'yé ye', meaning: 'Datuk (sebelah ayah)', exCn: '爷爷今年七十七岁。', exPy: 'Yéye jīnnián qīshíqī suì.', exBm: 'Datuk berumur 77 tahun pada tahun ini.' },
      { emoji: '👵', hanzi: '奶奶', pinyin: 'nǎi nai', meaning: 'Nenek (sebelah ayah)', exCn: '奶奶每天早上散步。', exPy: 'Nǎinai měitiān zǎoshang sànbù.', exBm: 'Nenek berjalan kaki setiap pagi.' },
      { emoji: '👨', hanzi: '爸爸', pinyin: 'bà ba', meaning: 'Ayah / Bapa', exCn: '爸爸在办公室工作。', exPy: 'Bàba zài bàngōngshì gōngzuò.', exBm: 'Ayah bekerja di pejabat.' },
      { emoji: '👩', hanzi: '妈妈', pinyin: 'mā ma', meaning: 'Ibu / Emak', exCn: '我爱我的爸爸和妈妈。', exPy: 'Wǒ ài wǒ de bàba hé māma.', exBm: 'Saya sayang ayah dan ibu saya.' },
      { emoji: '👧', hanzi: '姐姐', pinyin: 'jiě jie', meaning: 'Kakak', exCn: '姐姐在大学读书。', exPy: 'Jiějie zài dàxué dúshū.', exBm: 'Kakak belajar di universiti.' }
    ],
    s2: [
      { type: 'mcq', emoji: '👦', instruction: 'Pilih sebutan Pinyin yang betul bagi "abang"', hanzi: '哥哥', pinyin: 'Kosa kata Bab 4', speech: '哥哥', options: ['gē ge', 'gé gè', 'gě gē', 'gè ge'], answer: 'gē ge', explanation: '哥哥 dibaca gē ge (nada 1 + nada ringan).' },
      { type: 'mcq', emoji: '🧒', instruction: 'Apakah sebutan yang tepat bagi "adik lelaki"?', hanzi: '弟弟', pinyin: 'Kosa kata Bab 4', speech: '弟弟', options: ['dì di', 'dī dì', 'dí dǐ', 'tì ti'], answer: 'dì di', explanation: '弟弟 dibaca dì di (nada 4 + nada ringan).' },
      { type: 'mcq', emoji: '👧', instruction: 'Pilih Pinyin yang betul bagi "adik perempuan"', hanzi: '妹妹', pinyin: 'Kosa kata Bab 4', speech: '妹妹', options: ['mèi mei', 'měi mèi', 'mēi mēi', 'mài mai'], answer: 'mèi mei', explanation: '妹妹 dibaca mèi mei.' },
      { type: 'mcq', emoji: '🏡', instruction: 'Bagaimanakah sebutan bagi perkataan "tinggal"?', hanzi: '住', pinyin: 'Kosa kata Bab 4', speech: '住', options: ['zhù', 'zù', 'zhǔ', 'chù'], answer: 'zhù', explanation: '我住在吉隆坡 (Wǒ zhù zài Jílóngpō) bermaksud Saya tinggal di Kuala Lumpur.' },
      { type: 'mcq', emoji: '❤️', instruction: 'Pilih sebutan Pinyin yang betul bagi "sayang / cinta"', hanzi: '爱', pinyin: 'Kosa kata Bab 4', speech: '爱', options: ['ài', 'āi', 'ái', 'ǎi'], answer: 'ài', explanation: '爱 (ài) bernada keempat bermaksud sayang.' }
    ],
    s3: [
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 爸 (ayah)?', hanzi: '爸', pinyin: 'bà', speech: '爸', options: ['6 goresan', '7 goresan', '8 goresan', '9 goresan'], answer: '8 goresan', explanation: 'Aksara 爸 mempunyai 8 goresan (父 + 巴).' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 弟 (adik lelaki)?', hanzi: '弟', pinyin: 'dì', speech: '弟', options: ['6 goresan', '7 goresan', '8 goresan', '9 goresan'], answer: '7 goresan', explanation: 'Aksara 弟 mempunyai 7 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 家 (rumah/keluarga)?', hanzi: '家', pinyin: 'jiā', speech: '家', options: ['8 goresan', '9 goresan', '10 goresan', '11 goresan'], answer: '10 goresan', explanation: '家 mempunyai radikal 宀 (bumbung) di atas dan 豕 di bawah = 10 goresan.' },
      { type: 'mcq', emoji: '👩', instruction: 'Aksara manakah TIDAK mempunyai radikal perempuan (女)?', hanzi: 'Radikal 女', pinyin: 'Keluarga', speech: '爸', options: ['爸 (Ayah)', '妈 (Ibu)', '姐 (Kakak)', '妹 (Adik perempuan)'], answer: '爸 (Ayah)', explanation: '妈, 姐, 妹, dan 奶 semuanya mempunyai radikal 女, manakala 爸 mempunyai radikal 父.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 哥 (abang)?', hanzi: '哥', pinyin: 'gē', speech: '哥', options: ['8 goresan', '9 goresan', '10 goresan', '12 goresan'], answer: '10 goresan', explanation: '哥 terdiri daripada dua 可 yang disusun atas dan bawah = 10 goresan.' }
    ],
    s4: [
      { type: 'mcq', emoji: '👴', instruction: 'Apakah panggilan untuk bapa kepada ibu (datuk sebelah ibu)?', hanzi: '妈妈的爸爸', pinyin: 'Salasilah keluarga', speech: '外公', options: ['外公 (wài gōng)', '爷爷 (yé ye)', '伯伯 (bó bo)', '叔叔 (shū shu)'], answer: '外公 (wài gōng)', explanation: 'Datuk sebelah ayah = 爷爷; Datuk sebelah ibu = 外公.' },
      { type: 'mcq', emoji: '👵', instruction: 'Apakah panggilan untuk ibu kepada ibu (nenek sebelah ibu)?', hanzi: '妈妈的妈妈', pinyin: 'Salasilah keluarga', speech: '外婆', options: ['外婆 (wài pó)', '奶奶 (nǎi nai)', '姑姑 (gū gu)', '阿姨 (ā yí)'], answer: '外婆 (wài pó)', explanation: 'Nenek sebelah ayah = 奶奶; Nenek sebelah ibu = 外婆.' },
      { type: 'mcq', emoji: '👨', instruction: 'Apakah panggilan untuk abang kepada ayah (pak long)?', hanzi: '爸爸的哥哥', pinyin: 'Salasilah keluarga', speech: '伯伯', options: ['伯伯 (bó bo)', '叔叔 (shū shu)', '舅舅 (jiù jiu)', '堂哥 (táng gē)'], answer: '伯伯 (bó bo)', explanation: 'Abang ayah = 伯伯; Adik lelaki ayah = 叔叔; Saudara lelaki ibu = 舅舅.' },
      { type: 'mcq', emoji: '👩', instruction: 'Apakah panggilan untuk saudara perempuan ibu (mak cik)?', hanzi: '妈妈的姐妹', pinyin: 'Salasilah keluarga', speech: '阿姨', options: ['阿姨 (ā yí)', '姑姑 (gū gu)', '伯母 (bó mǔ)', '奶奶 (nǎi nai)'], answer: '阿姨 (ā yí)', explanation: 'Saudara perempuan ibu = 阿姨; Saudara perempuan ayah = 姑姑.' },
      { type: 'mcq', emoji: '👨‍👩‍👧‍👦', instruction: 'Pilih maksud perkataan ini', hanzi: '一家人', pinyin: 'yī jiā rén', speech: '一家人', options: ['Sekeluarga', 'Seorang diri', 'Rakan sekelas', 'Jiran tetangga'], answer: 'Sekeluarga', explanation: '我们一家人很快乐 bermaksud Kami sekeluarga sangat gembira.' }
    ],
    s5: [
      { type: 'mcq', emoji: '🖼️', instruction: 'A: 请问这位是谁？ (Menunjuk gambar datuk) — Pilih jawapan tepat:', hanzi: 'B: 这位是我的____。', pinyin: 'Beliau ialah datuk saya', speech: '这位是我的爷爷', options: ['爷爷', '妹妹', '弟弟', '姐姐'], answer: '爷爷', explanation: '这位 (zhè wèi) digunakan untuk merujuk orang secara hormat.' },
      { type: 'mcq', emoji: '🏙️', instruction: 'A: 你住在哪里？ (Di manakah awak tinggal?) — Pilih jawapan lengkap:', hanzi: 'B: 我____吉隆坡。', pinyin: 'Tinggal di Kuala Lumpur', speech: '我住在吉隆坡', options: ['住在', '有在', '叫在', '爱在'], answer: '住在', explanation: '我住在吉隆坡 (Wǒ zhù zài Jílóngpō).' },
      { type: 'mcq', emoji: '🔢', instruction: 'A: 你有几个姐姐？ — Pilih penjodoh bilangan yang tepat:', hanzi: 'B: 我有四____姐姐。', pinyin: 'Penjodoh bilangan untuk orang', speech: '我有四个姐姐', options: ['个 (gè)', '本 (běn)', '张 (zhāng)', '杯 (bēi)'], answer: '个 (gè)', explanation: '个 (gè) ialah penjodoh bilangan umum untuk ahli keluarga dan manusia.' },
      { type: 'mcq', emoji: '👦', instruction: 'Untuk bilangan "dua orang abang", perkataan yang betul ialah...', hanzi: '我有____个哥哥。', pinyin: 'Dua orang', speech: '我有两个哥哥', options: ['两 (liǎng)', '二 (èr)', '双 (shuāng)', '十 (shí)'], answer: '两 (liǎng)', explanation: 'Sebelum penjodoh bilangan 个, angka 2 ditulis sebagai 两 (两个哥哥).' },
      { type: 'mcq', emoji: '❓', instruction: 'Untuk bertanya umur datuk secara sopan, kita bertanya:', hanzi: '爷爷今年____了？', pinyin: 'Berapa umur datuk tahun ini?', speech: '爷爷今年多大了', options: ['多大 (duō dà)', '几岁 (jǐ suì)', '什么 (shén me)', '哪里 (nǎ lǐ)'], answer: '多大 (duō dà)', explanation: '多大 (duō dà) digunakan untuk bertanya umur orang dewasa atau warga emas dengan sopan.' }
    ],
    s6: [
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Saya tinggal di Melaka', speech: '我住在马六甲', words: ['马六甲', '住在', '我'], answerOrder: ['我', '住在', '马六甲'], explanation: '我 (Saya) + 住在 (tinggal di) + 马六甲 (Melaka).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Keluarga saya mempunyai sembilan orang', speech: '我的家有九个人', words: ['九个人', '我的家', '有'], answerOrder: ['我的家', '有', '九个人'], explanation: '我的家 (Keluarga saya) + 有 (mempunyai) + 九个人 (9 orang).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Saya sayang ahli keluarga saya', speech: '我爱我的家人', words: ['我的家人', '爱', '我'], answerOrder: ['我', '爱', '我的家人'], explanation: '我 (Saya) + 爱 (sayang) + 我的家人 (ahli keluarga saya).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Beliau ialah ibu saya', speech: '这位是我的妈妈', words: ['我的妈妈', '这位', '是'], answerOrder: ['这位', '是', '我的妈妈'], explanation: '这位 (Beliau) + 是 (ialah) + 我的妈妈 (ibu saya).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Kami sekeluarga sangat gembira', speech: '我们一家人很快乐', words: ['很快乐', '我们', '一家人'], answerOrder: ['我们', '一家人', '很快乐'], explanation: '我们一家人 (Kami sekeluarga) + 很快乐 (sangat gembira).' }
    ],
    s7: [
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '我的名字叫丽丽。我住在吉隆坡。我的家有九个人。我有爷爷、奶奶、爸爸、妈妈、一个哥哥、一个姐姐、一个弟弟和一个妹妹。', speech: '我的名字叫丽丽。我住在吉隆坡。我的家有九个人。', question: 'Berapakah bilangan ahli keluarga Lili kesemuanya?', options: ['9 orang (九个人)', '7 orang', '8 orang', '10 orang'], answer: '9 orang (九个人)', explanation: '我的家有九个人 bermaksud keluarga saya mempunyai sembilan orang.' },
      { type: 'comprehension', instruction: 'Baca carta keluarga Li Duan dan jawab soalan', passage: '李端今年20岁。他的姐姐叫李丽，今年22岁。他的爸爸48岁，妈妈47岁。', speech: '李端今年二十岁。他的姐姐今年二十二岁。', question: 'Berapakah umur kakak Li Duan?', options: ['22 tahun', '20 tahun', '25 tahun', '18 tahun'], answer: '22 tahun', explanation: '姐姐 (22岁) bermaksud kakak berumur 22 tahun.' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '周末时，艾曼和家人一起回外公和外婆的家。外公今年七十五岁，身体很健康。', speech: '周末时，艾曼和家人一起回外公和外婆的家。', question: 'Rumah siapakah yang dilawati oleh keluarga Aiman pada hujung minggu?', options: ['Datuk dan nenek sebelah ibu (外公和外婆)', 'Pak long (伯伯)', 'Cikgu (老师)', 'Rakan sekelas (同学)'], answer: 'Datuk dan nenek sebelah ibu (外公和外婆)', explanation: '外公 dan 外婆 ialah datuk dan nenek sebelah ibu.' },
      { type: 'comprehension', instruction: 'Baca teka-teki salasilah keluarga', passage: '妈妈的兄弟叫舅舅，那么妈妈的姐妹叫什么呢？', speech: '妈妈的兄弟叫舅舅，妈妈的姐妹叫什么呢？', question: 'Apakah panggilan untuk saudara perempuan ibu?', options: ['阿姨 (ā yí)', '姑姑 (gū gu)', '奶奶 (nǎi nai)', '伯母 (bó mǔ)'], answer: '阿姨 (ā yí)', explanation: '妈妈的姐妹叫阿姨 (Saudara perempuan ibu dipanggil mak cik / 阿姨).' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '哈山有一个哥哥和一个妹妹。哥哥在中学读书，妹妹今年七岁。', speech: '哈山有一个哥哥和一个妹妹。妹妹今年七岁。', question: 'Berapakah umur adik perempuan Hassan?', options: ['7 tahun (七岁)', '13 tahun', '10 tahun', '5 tahun'], answer: '7 tahun (七岁)', explanation: '妹妹今年七岁 bermaksud adik perempuan berumur 7 tahun tahun ini.' }
    ]
  },

  // =======================================================
  // BAB 5: 课室里 (Di Dalam Kelas — Peralatan & Arahan Kelas)
  // =======================================================
  c5: {
    s1: [
      { emoji: '⬜', hanzi: '白板', pinyin: 'bái bǎn', meaning: 'Papan putih', exCn: '老师在白板上写字。', exPy: 'Lǎoshī zài báibǎn shang xiězì.', exBm: 'Cikgu menulis perkataan di atas papan putih.' },
      { emoji: '🪑', hanzi: '桌子', pinyin: 'zhuō zi', meaning: 'Meja', exCn: '课室里有很多桌子。', exPy: 'Kèshì lǐ yǒu hěn duō zhuōzi.', exBm: 'Di dalam bilik darjah ada banyak meja.' },
      { emoji: '🎒', hanzi: '书包', pinyin: 'shū bāo', meaning: 'Beg sekolah', exCn: '我的书包里有字典。', exPy: 'Wǒ de shūbāo lǐ yǒu zìdiǎn.', exBm: 'Di dalam beg sekolah saya ada kamus.' },
      { emoji: '✏️', hanzi: '铅笔', pinyin: 'qiān bǐ', meaning: 'Pensel', exCn: '这支铅笔很长。', exPy: 'Zhè zhī qiānbǐ hěn cháng.', exBm: 'Pensel ini sangat panjang.' },
      { emoji: '📖', hanzi: '字典', pinyin: 'zì diǎn', meaning: 'Kamus', exCn: '我有一本华语字典。', exPy: 'Wǒ yǒu yī běn Huáyǔ zìdiǎn.', exBm: 'Saya mempunyai sebuah kamus bahasa Cina.' }
    ],
    s2: [
      { type: 'mcq', emoji: '⏰', instruction: 'Pilih sebutan Pinyin yang betul bagi "jam dinding"', hanzi: '时钟', pinyin: 'Kosa kata Bab 5', speech: '时钟', options: ['shí zhōng', 'sì zhōng', 'shí zōng', 'shǐ zhòng'], answer: 'shí zhōng', explanation: '时钟 (shí zhōng) bermaksud jam.' },
      { type: 'mcq', emoji: '🗑️', instruction: 'Bagaimanakah sebutan bagi "tong sampah"?', hanzi: '垃圾桶', pinyin: 'Kosa kata Bab 5', speech: '垃圾桶', options: ['lā jī tǒng', 'lè jī tǒng', 'lā jí dòng', 'nà jī tǒng'], answer: 'lā jī tǒng', explanation: '垃圾桶 (lā jī tǒng) bermaksud tong sampah.' },
      { type: 'mcq', emoji: '🧹', instruction: 'Pilih Pinyin yang tepat bagi "penyapu"', hanzi: '扫帚', pinyin: 'Kosa kata Bab 5', speech: '扫帚', options: ['sào zhou', 'sǎo zhǒu', 'shào zhou', 'sāo zòu'], answer: 'sào zhou', explanation: '扫帚 dibaca sào zhou (suku kata kedua bernada ringan).' },
      { type: 'mcq', emoji: '📏', instruction: 'Pilih sebutan yang betul bagi "pembaris"', hanzi: '尺', pinyin: 'Kosa kata Bab 5', speech: '尺', options: ['chǐ', 'cǐ', 'chī', 'shǐ'], answer: 'chǐ', explanation: '尺 (chǐ) bernada ketiga bermaksud pembaris.' },
      { type: 'mcq', emoji: '📓', instruction: 'Apakah sebutan bagi "buku latihan"?', hanzi: '簿子', pinyin: 'Kosa kata Bab 5', speech: '簿子', options: ['bù zi', 'bǔ zǐ', 'pù zi', 'bó zi'], answer: 'bù zi', explanation: '簿子 (bù zi) bermaksud buku latihan.' }
    ],
    s3: [
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 小 (kecil)?', hanzi: '小', pinyin: 'xiǎo', speech: '小', options: ['3 goresan', '4 goresan', '5 goresan', '6 goresan'], answer: '3 goresan', explanation: '小 ditulis dengan 3 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 长 (panjang)?', hanzi: '长', pinyin: 'cháng', speech: '长', options: ['3 goresan', '4 goresan', '5 goresan', '6 goresan'], answer: '4 goresan', explanation: 'Aksara 长 mempunyai 4 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 室 (bilik/kelas)?', hanzi: '室', pinyin: 'shì', speech: '室', options: ['8 goresan', '9 goresan', '10 goresan', '11 goresan'], answer: '9 goresan', explanation: '室 terdiri daripada 宀 (3 goresan) + 至 (6 goresan) = 9 goresan.' },
      { type: 'mcq', emoji: '🧩', instruction: 'Apakah radikal bagi perkataan 桌 (meja) dan 椅 (kerusi)?', hanzi: '木', pinyin: 'mùzìpáng', speech: '桌子', options: ['Kayu (木)', 'Logam (钅)', 'Air (氵)', 'Tanah (土)'], answer: 'Kayu (木)', explanation: 'Perabot seperti 桌子 (meja), 椅子 (kerusi), dan 橱 (almari) mempunyai radikal kayu (木).' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 短 (pendek)?', hanzi: '短', pinyin: 'duǎn', speech: '短', options: ['10 goresan', '11 goresan', '12 goresan', '13 goresan'], answer: '12 goresan', explanation: 'Aksara 短 mempunyai 12 goresan.' }
    ],
    s4: [
      { type: 'mcq', emoji: '🌀', instruction: 'Pilih maksud peralatan elektrik di siling kelas ini', hanzi: '风扇', pinyin: 'fēng shàn', speech: '风扇', options: ['Kipas', 'Lampu', 'Penyaman udara', 'Jam'], answer: 'Kipas', explanation: '风扇 (fēng shàn) = kipas; 电灯 (diàn dēng) = lampu.' },
      { type: 'mcq', emoji: '📌', instruction: 'Pilih maksud perkataan ini', hanzi: '布告板', pinyin: 'bù gào bǎn', speech: '布告板', options: ['Papan kenyataan', 'Papan putih', 'Pemadam papan', 'Kalendar'], answer: 'Papan kenyataan', explanation: '布告板 (bù gào bǎn) bermaksud papan kenyataan.' },
      { type: 'mcq', emoji: '🧽', instruction: 'Pilih maksud alat tulis ini', hanzi: '橡皮', pinyin: 'xiàng pí', speech: '橡皮', options: ['Pemadam', 'Pensel', 'Pembaris', 'Kamus'], answer: 'Pemadam', explanation: '橡皮 (xiàng pí) bermaksud pemadam getah.' },
      { type: 'mcq', emoji: '🚪', instruction: 'Pilih maksud bahagian bilik darjah ini', hanzi: '窗', pinyin: 'chuāng', speech: '窗', options: ['Tingkap', 'Pintu', 'Almari', 'Lantai'], answer: 'Tingkap', explanation: '窗 (chuāng) = tingkap; 门 (mén) = pintu.' },
      { type: 'mcq', emoji: '📚', instruction: 'Penjodoh bilangan untuk buku (书), buku latihan (簿子) dan kamus (字典) ialah...', hanzi: '一____书', pinyin: 'Penjodoh bilangan buku', speech: '一本书', options: ['本 (běn)', '张 (zhāng)', '把 (bǎ)', '支 (zhī)'], answer: '本 (běn)', explanation: '一本书 (yī běn shū) = sebuah buku; 一张桌子 (sebuah meja); 一把椅子 (sebuah kerusi).' }
    ],
    s5: [
      { type: 'mcq', emoji: '🧹', instruction: 'Guru meminta murid menyapu lantai kelas:', hanzi: '请你____。', pinyin: 'Tolong sapu lantai', speech: '请你扫地', options: ['扫地 (sǎo dì)', '擦白板 (cā báibǎn)', '关门 (guān mén)', '开窗 (kāi chuāng)'], answer: '扫地 (sǎo dì)', explanation: '请你扫地 (Qǐng nǐ sǎo dì) bermaksud Sila sapu lantai.' },
      { type: 'mcq', emoji: '🤫', instruction: 'Apabila keadaan di dalam perpustakaan bising, pengawas berkata:', hanzi: '请____！', pinyin: 'Sila senyap', speech: '请安静', options: ['安静 (ān jìng)', '举手 (jǔ shǒu)', '站起来 (zhàn qǐlái)', '出去 (chū qù)'], answer: '安静 (ān jìng)', explanation: '请安静 (Qǐng ānjìng) bermaksud Sila senyap.' },
      { type: 'mcq', emoji: '🙋', instruction: 'Guru bertanya soalan dan meminta murid mengangkat tangan:', hanzi: '请____！', pinyin: 'Sila angkat tangan', speech: '请举手', options: ['举手 (jǔ shǒu)', '坐下 (zuò xià)', '进来 (jìn lái)', '关门 (guān mén)'], answer: '举手 (jǔ shǒu)', explanation: '请举手 (Qǐng jǔ shǒu) bermaksud Sila angkat tangan.' },
      { type: 'mcq', emoji: '🪟', instruction: 'Cuaca di dalam kelas panas. Guru menyuruh murid membuka tingkap:', hanzi: '请你____。', pinyin: 'Tolong buka tingkap', speech: '请你开窗', options: ['开窗 (kāi chuāng)', '关门 (guān mén)', '擦白板 (cā báibǎn)', '坐下 (zuò xià)'], answer: '开窗 (kāi chuāng)', explanation: '请你开窗 (Qǐng nǐ kāi chuāng) bermaksud Sila buka tingkap.' },
      { type: 'mcq', emoji: '🪑', instruction: 'Penjodoh bilangan yang betul bagi meja (桌子) ialah...', hanzi: '课室里有一____桌子。', pinyin: 'Sebuah meja', speech: '课室里有一张桌子', options: ['张 (zhāng)', '把 (bǎ)', '本 (běn)', '个 (gè)'], answer: '张 (zhāng)', explanation: '一张桌子 (yī zhāng zhuōzi) = sebuah meja; 一把椅子 (yī bǎ yǐzi) = sebuah kerusi.' }
    ],
    s6: [
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Saya mempunyai sebuah kamus', speech: '我有一本字典', words: ['字典', '一本', '我有'], answerOrder: ['我有', '一本', '字典'], explanation: '我有 (Saya mempunyai) + 一本 (sebuah) + 字典 (kamus).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Di dalam kelas ada papan putih', speech: '课室里有白板', words: ['白板', '课室里', '有'], answerOrder: ['课室里', '有', '白板'], explanation: '课室里 (Di dalam kelas) + 有 (ada) + 白板 (papan putih).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Pembaris kakak sangat panjang', speech: '姐姐的尺很长', words: ['很长', '尺', '姐姐的'], answerOrder: ['姐姐的', '尺', '很长'], explanation: '姐姐的尺 (Pembaris kakak) + 很长 (sangat panjang).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Beg sekolah adik sangat besar', speech: '弟弟的书包很大', words: ['很大', '书包', '弟弟的'], answerOrder: ['弟弟的', '书包', '很大'], explanation: '弟弟的书包 (Beg sekolah adik) + 很大 (sangat besar).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Tolong padam papan putih', speech: '请你擦白板', words: ['擦白板', '请你'], answerOrder: ['请你', '擦白板'], explanation: '请你 (Sila / Tolong anda) + 擦白板 (padam papan putih).' }
    ],
    s7: [
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '我的课室很干净。课室里有白板、时钟、布告板、风扇和电灯。课室前面有一张大桌子，后面有一个垃圾桶和两把扫帚。', speech: '课室前面有一张大桌子，后面有一个垃圾桶和两把扫帚。', question: 'Apakah yang terletak di bahagian belakang bilik darjah?', options: ['Tong sampah dan penyapu', 'Papan putih dan jam', 'Meja besar dan kipas', 'Papan kenyataan'], answer: 'Tong sampah dan penyapu', explanation: '后面有一个垃圾桶和两把扫帚 bermaksud di belakang ada satu tong sampah dan dua batang penyapu.' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '丽莎的书包里有三本书、两本簿子、一支铅笔、一块橡皮和一把尺。', speech: '丽莎的书包里有三本书、两本簿子和一支铅笔。', question: 'Berapa buahkah buku latihan (簿子) di dalam beg sekolah Lisa?', options: ['2 buah (两本)', '3 buah (三本)', '1 buah (一本)', '4 buah (四本)'], answer: '2 buah (两本)', explanation: '两本簿子 bermaksud dua buah buku latihan.' },
      { type: 'comprehension', instruction: 'Baca situasi dan jawab soalan', passage: '上课钟声响了，班长说：“请安静，老师进来了！”同学们马上站起来行礼。', speech: '班长说：请安静，老师进来了！', question: 'Apakah yang dilakukan oleh murid-murid apabila guru masuk ke kelas?', options: ['Berdiri dan memberi hormat', 'Menyapu lantai', 'Menutup pintu', 'Mengemas beg'], answer: 'Berdiri dan memberi hormat', explanation: '站起来行礼 bermaksud berdiri dan memberi hormat.' },
      { type: 'comprehension', instruction: 'Baca dialog dan jawab soalan', passage: '堂哥：你的书包里有什么？\n弟弟：我的书包里有一本华语字典和几本书。', speech: '我的书包里有一本华语字典和几本书。', question: 'Apakah barang yang disebut oleh adik lelaki di dalam begnya?', options: ['Kamus bahasa Cina dan buku', 'Pembaris dan pemadam', 'Kalendar dan jam', 'Makanan dan minuman'], answer: 'Kamus bahasa Cina dan buku', explanation: '一本华语字典和几本书 bermaksud sebuah kamus bahasa Cina dan beberapa buah buku.' },
      { type: 'comprehension', instruction: 'Baca arahan tugas harian dan jawab soalan', passage: '今天是星期四，轮到艾曼和哈山值日。艾曼负责擦白板，哈山负责扫地和开窗。', speech: '艾曼负责擦白板，哈山负责扫地和开窗。', question: 'Apakah tugas Aiman pada hari Khamis?', options: ['Memadam papan putih (擦白板)', 'Menyapu lantai (扫地)', 'Membuka tingkap (开窗)', 'Menutup pintu (关门)'], answer: 'Memadam papan putih (擦白板)', explanation: '艾曼负责擦白板 bermaksud Aiman bertugas memadam papan putih.' }
    ]
  },

  // =======================================================
  // BAB 6: 我的学校 (Sekolah Saya — Kemudahan & Kata Arah)
  // =======================================================
  c6: {
    s1: [
      { emoji: '📚', hanzi: '图书馆', pinyin: 'tú shū guǎn', meaning: 'Perpustakaan', exCn: '我去图书馆借书。', exPy: 'Wǒ qù túshūguǎn jiè shū.', exBm: 'Saya pergi ke perpustakaan meminjam buku.' },
      { emoji: '🍽️', hanzi: '食堂', pinyin: 'shí táng', meaning: 'Kantin', exCn: '下课了，我们去食堂。', exPy: 'Xiàkè le, wǒmen qù shítáng.', exBm: 'Sudah waktu rehat, mari kita ke kantin.' },
      { emoji: '🏟️', hanzi: '礼堂', pinyin: 'lǐ táng', meaning: 'Dewan sekolah', exCn: '周会在学校礼堂举行。', exPy: 'Zhōuhuì zài xuéxiào lǐtáng jǔxíng.', exBm: 'Perhimpunan mingguan diadakan di dewan sekolah.' },
      { emoji: '🔬', hanzi: '实验室', pinyin: 'shí yàn shì', meaning: 'Makmal sains', exCn: '我们在实验室上科学课。', exPy: 'Wǒmen zài shíyànshì shàng kēxuékè.', exBm: 'Kami belajar subjek sains di makmal.' },
      { emoji: '💻', hanzi: '电脑室', pinyin: 'diàn nǎo shì', meaning: 'Bilik komputer', exCn: '办公室的前面是电脑室。', exPy: 'Bàngōngshì de qiánmiàn shì diànnǎoshì.', exBm: 'Di hadapan pejabat ialah bilik komputer.' }
    ],
    s2: [
      { type: 'mcq', emoji: '🏠', instruction: 'Pilih sebutan Pinyin yang betul bagi "asrama"', hanzi: '宿舍', pinyin: 'Kosa kata Bab 6', speech: '宿舍', options: ['sù shè', 'shù sè', 'sǔ shé', 'xù shè'], answer: 'sù shè', explanation: '宿舍 (sù shè) bermaksud asrama.' },
      { type: 'mcq', emoji: '⚽', instruction: 'Bagaimanakah sebutan bagi "padang rumput"?', hanzi: '草场', pinyin: 'Kosa kata Bab 6', speech: '草场', options: ['cǎo chǎng', 'chǎo cǎng', 'cāo cháng', 'zǎo chǎng'], answer: 'cǎo chǎng', explanation: '草场 (cǎo chǎng) bermaksud padang.' },
      { type: 'mcq', emoji: '🏀', instruction: 'Pilih Pinyin yang tepat bagi "gelanggang bola keranjang"', hanzi: '篮球场', pinyin: 'Kosa kata Bab 6', speech: '篮球场', options: ['lán qiú chǎng', 'nán qiú chǎng', 'lán jiǔ chǎng', 'lǎn qiú cháng'], answer: 'lán qiú chǎng', explanation: '篮球场 (lán qiú chǎng) bermaksud gelanggang bola keranjang.' },
      { type: 'mcq', emoji: '🚗', instruction: 'Pilih sebutan yang betul bagi "tempat letak kereta"', hanzi: '停车场', pinyin: 'Kosa kata Bab 6', speech: '停车场', options: ['tíng chē chǎng', 'tīng chě cháng', 'dìng chē chǎng', 'tíng cē chǎng'], answer: 'tíng chē chǎng', explanation: '停车场 (tíng chē chǎng) bermaksud tempat parkir kereta.' },
      { type: 'mcq', emoji: '📖', instruction: 'Apakah sebutan bagi frasa "mengulang kaji pelajaran"?', hanzi: '温习功课', pinyin: 'Kosa kata Bab 6', speech: '温习功课', options: ['wēn xí gōng kè', 'wèn xǐ gōng kè', 'wēn jí gōng kè', 'yùn xí gōng kè'], answer: 'wēn xí gōng kè', explanation: '温习功课 (wēn xí gōng kè) bermaksud mengulang kaji pelajaran.' }
    ],
    s3: [
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 去 (pergi)?', hanzi: '去', pinyin: 'qù', speech: '去', options: ['4 goresan', '5 goresan', '6 goresan', '7 goresan'], answer: '5 goresan', explanation: 'Aksara 去 mempunyai 5 goresan (土 + 厶).' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 左 (kiri) dan 右 (kanan)?', hanzi: '左 / 右', pinyin: 'zuǒ / yòu', speech: '左边', options: ['5 goresan', '4 goresan', '6 goresan', '7 goresan'], answer: '5 goresan', explanation: 'Kedua-dua aksara 左 dan 右 mempunyai 5 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 前 (hadapan)?', hanzi: '前', pinyin: 'qián', speech: '前面', options: ['8 goresan', '9 goresan', '10 goresan', '11 goresan'], answer: '9 goresan', explanation: 'Aksara 前 mempunyai 9 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 后 (belakang)?', hanzi: '后', pinyin: 'hòu', speech: '后面', options: ['5 goresan', '6 goresan', '7 goresan', '8 goresan'], answer: '6 goresan', explanation: 'Aksara 后 ditulis dengan 6 goresan.' },
      { type: 'mcq', emoji: '🧩', instruction: 'Lengkapkan pasangan perkataan: 教___办公室', hanzi: '教____办公室', pinyin: 'Bilik guru', speech: '教师办公室', options: ['师 (shī)', '室 (shì)', '学 (xué)', '校 (xiào)'], answer: '师 (shī)', explanation: '教师办公室 (jiàoshī bàngōngshì) bermaksud bilik guru.' }
    ],
    s4: [
      { type: 'mcq', emoji: '🏢', instruction: 'Pilih maksud tempat di sekolah ini', hanzi: '办公室', pinyin: 'bàn gōng shì', speech: '办公室', options: ['Pejabat', 'Bilik darjah', 'Asrama', 'Tandas'], answer: 'Pejabat', explanation: '办公室 (bàn gōng shì) bermaksud pejabat.' },
      { type: 'mcq', emoji: '🚻', instruction: 'Pilih maksud kemudahan sekolah ini', hanzi: '厕所', pinyin: 'cè suǒ', speech: '厕所', options: ['Tandas', 'Dewan', 'Kantin', 'Makmal'], answer: 'Tandas', explanation: '厕所 (cè suǒ) bermaksud tandas.' },
      { type: 'mcq', emoji: '⬅️', instruction: 'Pilih maksud kata arah ini', hanzi: '左边', pinyin: 'zuǒ biān', speech: '左边', options: ['Sebelah kiri', 'Sebelah kanan', 'Hadapan', 'Belakang'], answer: 'Sebelah kiri', explanation: '左边 (zuǒ biān) = sebelah kiri; 右边 (yòu biān) = sebelah kanan.' },
      { type: 'mcq', emoji: '⬆️', instruction: 'Pilih maksud kata arah ini', hanzi: '前面', pinyin: 'qián miàn', speech: '前面', options: ['Hadapan', 'Belakang', 'Atas', 'Bawah'], answer: 'Hadapan', explanation: '前面 (qián miàn) = hadapan; 后面 (hòu miàn) = belakang.' },
      { type: 'mcq', emoji: '📕', instruction: 'Pilih maksud kata kerja ini', hanzi: '借书', pinyin: 'jiè shū', speech: '借书', options: ['Meminjam buku', 'Membeli buku', 'Menulis buku', 'Menjual buku'], answer: 'Meminjam buku', explanation: '去图书馆借书 bermaksud pergi ke perpustakaan meminjam buku.' }
    ],
    s5: [
      { type: 'mcq', emoji: '🗺️', instruction: '丽欣: 你要去哪里？ — Pilih jawapan tepat daripada 木都:', hanzi: '木都: 我要去图书馆____。', pinyin: 'Meminjam buku', speech: '我要去图书馆借书', options: ['借书', '吃东西', '打篮球', '睡觉'], answer: '借书', explanation: 'Di perpustakaan (图书馆), aktiviti utama ialah 借书 (meminjam buku) atau 温习功课.' },
      { type: 'mcq', emoji: '🧭', instruction: 'Berdasarkan pelan sekolah, di manakah bilik komputer?', hanzi: '办公室的____是电脑室。', pinyin: 'Di hadapan pejabat', speech: '办公室的前面是电脑室', options: ['前面 (Hadapan)', '后面 (Belakang)', '左边 (Kiri)', '右边 (Kanan)'], answer: '前面 (Hadapan)', explanation: '办公室的前面是电脑室 (Di hadapan pejabat ialah bilik komputer).' },
      { type: 'mcq', emoji: '🏀', instruction: 'Berdasarkan pelan sekolah, di manakah gelanggang bola keranjang?', hanzi: '办公室的____是篮球场。', pinyin: 'Di sebelah kanan pejabat', speech: '办公室的右边是篮球场', options: ['右边 (Sebelah kanan)', '左边 (Sebelah kiri)', '前面 (Hadapan)', '后面 (Belakang)'], answer: '右边 (Sebelah kanan)', explanation: '办公室的右边是篮球场 (Di sebelah kanan pejabat ialah gelanggang bola keranjang).' },
      { type: 'mcq', emoji: '🤝', instruction: 'Untuk mengajak kawan pergi bersama-sama:', hanzi: '我们____去吧！', pinyin: 'Mari kita pergi bersama-sama!', speech: '我们一起去吧', options: ['一起 (yī qǐ)', '哪里 (nǎ lǐ)', '什么 (shén me)', '多少 (duō shǎo)'], answer: '一起 (yī qǐ)', explanation: '我们一起去吧 (Wǒmen yīqǐ qù ba) bermaksud Mari kita pergi bersama!' },
      { type: 'mcq', emoji: '🛏️', instruction: 'Murid sekolah berasrama penuh berehat dan tidur di...', hanzi: '我们在____休息。', pinyin: 'Berehat di asrama', speech: '我们在宿舍休息', options: ['宿舍 (sù shè)', '停车场 (tíngchēchǎng)', '厕所 (cèsuǒ)', '草场 (cǎochǎng)'], answer: '宿舍 (sù shè)', explanation: '宿舍 (sù shè) bermaksud asrama.' }
    ],
    s6: [
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Awak mahu pergi ke mana?', speech: '你要去哪里', words: ['哪里', '要去', '你'], answerOrder: ['你', '要去', '哪里'], explanation: '你 (Awak) + 要去 (mahu pergi) + 哪里 (ke mana).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Saya mahu pergi ke kantin', speech: '我要去食堂', words: ['食堂', '我要', '去'], answerOrder: ['我要', '去', '食堂'], explanation: '我要 (Saya mahu) + 去 (pergi) + 食堂 (kantin).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Di sebelah kiri pejabat ialah asrama', speech: '办公室的左边是宿舍', words: ['是宿舍', '办公室的', '左边'], answerOrder: ['办公室的', '左边', '是宿舍'], explanation: '办公室的左边 (Sebelah kiri pejabat) + 是宿舍 (ialah asrama).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Saya dan kawan membaca buku di perpustakaan', speech: '我和朋友在图书馆看书', words: ['在图书馆看书', '我和', '朋友'], answerOrder: ['我和', '朋友', '在图书馆看书'], explanation: 'Subjek 我和朋友 + Lokasi 在图书馆 + Aktiviti 看书.' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Mari kita pergi ulang kaji bersama', speech: '我们一起去温习功课', words: ['温习功课', '我们', '一起去'], answerOrder: ['我们', '一起去', '温习功课'], explanation: '我们 (Kita) + 一起去 (pergi bersama) + 温习功课 (ulang kaji pelajaran).' }
    ],
    s7: [
      { type: 'comprehension', instruction: 'Baca dialog dan jawab soalan', passage: '丽欣：你要去哪里？\n木都：我要去图书馆借书。你呢？\n丽欣：我要去图书馆温习功课。\n木都：我们一起去吧！', speech: '木都说：我要去图书馆借书。丽欣说：我要去图书馆温习功课。', question: 'Apakah tujuan Li Xin pergi ke perpustakaan?', options: ['Mengulang kaji pelajaran (温习功课)', 'Meminjam buku (借书)', 'Makan tengah hari', 'Bermain bola keranjang'], answer: 'Mengulang kaji pelajaran (温习功课)', explanation: 'Li Xin berkata: 我要去图书馆温习功课.' },
      { type: 'comprehension', instruction: 'Baca petikan lokasi dan jawab soalan', passage: '办公室的前面是电脑室，后面是停车场，左边是宿舍，右边是篮球场。', speech: '办公室的前面是电脑室，后面是停车场。', question: 'Apakah kemudahan yang terletak di belakang pejabat?', options: ['Tempat parkir kereta (停车场)', 'Bilik komputer (电脑室)', 'Asrama (宿舍)', 'Gelanggang bola keranjang (篮球场)'], answer: 'Tempat parkir kereta (停车场)', explanation: '后面是停车场 bermaksud di belakangnya ialah tempat parkir kereta.' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '我的学校很大，也很美丽。学校里有礼堂、实验室、图书馆、食堂和草场。下午四点，男同学们喜欢在草场上踢足球。', speech: '下午四点，男同学们喜欢在草场上踢足球。', question: 'Bilakah murid-murid lelaki bermain bola sepak di padang?', options: ['Pukul 4:00 petang', 'Pukul 7:00 pagi', 'Pukul 12:30 tengah hari', 'Pukul 8:00 malam'], answer: 'Pukul 4:00 petang', explanation: '下午四点 bermaksud pukul 4 petang.' },
      { type: 'comprehension', instruction: 'Baca borang penyertaan dan jawab soalan', passage: '马来西亚全寄宿中学第六届中华文化节参加表格：学生须填写名字、学校、性别、年龄、班级和出生日期。', speech: '学生须填写名字、学校、性别、年龄、班级和出生日期。', question: 'Apakah maklumat "班级" yang perlu diisi dalam borang tersebut?', options: ['Kelas / Tingkatan', 'Nombor telefon', 'Alamat rumah', 'Tandatangan ibu bapa'], answer: 'Kelas / Tingkatan', explanation: '班级 (bān jí) bermaksud kelas atau tingkatan.' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '老师在教师办公室里批改作业。下课后，班长把簿子送到教师办公室给老师。', speech: '下课后，班长把簿子送到教师办公室给老师。', question: 'Ke manakah ketua kelas menghantar buku latihan selepas tamat kelas?', options: ['Ke bilik guru (教师办公室)', 'Ke makmal (实验室)', 'Ke dewan (礼堂)', 'Ke asrama (宿舍)'], answer: 'Ke bilik guru (教师办公室)', explanation: '送到教师办公室 bermaksud menghantar ke bilik guru.' }
    ]
  },

  // =======================================================
  // BAB 7: 去食堂 (Ke Kantin — Makanan, Harga & Penjodoh Bilangan)
  // =======================================================
  c7: {
    s1: [
      { emoji: '🍗', hanzi: '海南鸡饭', pinyin: 'hǎi nán jī fàn', meaning: 'Nasi ayam Hainan', exCn: '我想吃海南鸡饭。', exPy: 'Wǒ xiǎng chī Hǎinán jīfàn.', exBm: 'Saya ingin makan nasi ayam Hainan.' },
      { emoji: '🍜', hanzi: '咖喱米粉', pinyin: 'gā lí mǐ fěn', meaning: 'Mihun kari', exCn: '一碗咖喱米粉三令吉。', exPy: 'Yī wǎn gālí mǐfěn sān lìngjí.', exBm: 'Semangkuk mihun kari berharga RM3.' },
      { emoji: '🍊', hanzi: '橙汁', pinyin: 'chéng zhī', meaning: 'Jus oren', exCn: '这里的橙汁很新鲜。', exPy: 'Zhèlǐ de chéngzhī hěn xīnxiān.', exBm: 'Jus oren di sini sangat segar.' },
      { emoji: '🥥', hanzi: '椰浆饭', pinyin: 'yē jiāng fàn', meaning: 'Nasi lemak', exCn: '食堂有卖椰浆饭。', exPy: 'Shítáng yǒu mài yējiāngfàn.', exBm: 'Kantin ada menjual nasi lemak.' },
      { emoji: '💰', hanzi: '多少钱', pinyin: 'duō shǎo qián', meaning: 'Berapa harga', exCn: '请问一共多少钱？', exPy: 'Qǐngwèn yīgòng duōshǎo qián?', exBm: 'Tumpang tanya, berapakah jumlah harganya?' }
    ],
    s2: [
      { type: 'mcq', emoji: '🍢', instruction: 'Pilih sebutan Pinyin yang betul bagi "sate"', hanzi: '沙爹', pinyin: 'Kosa kata Bab 7', speech: '沙爹', options: ['shā diē', 'sā diē', 'shà tiē', 'shǎ dié'], answer: 'shā diē', explanation: '沙爹 (shā diē) ialah transliterasi rasmi bagi sate.' },
      { type: 'mcq', emoji: '🥞', instruction: 'Bagaimanakah sebutan bagi "roti canai"?', hanzi: '印度煎饼', pinyin: 'Kosa kata Bab 7', speech: '印度煎饼', options: ['yìn dù jiān bǐng', 'yīng dù jiān bǐng', 'yìn tù qiān bǐng', 'yǐn dù jiàn píng'], answer: 'yìn dù jiān bǐng', explanation: '印度煎饼 (yìn dù jiān bǐng) bermaksud roti canai.' },
      { type: 'mcq', emoji: '🍝', instruction: 'Pilih Pinyin yang tepat bagi "kuetiau goreng"', hanzi: '炒粿条', pinyin: 'Kosa kata Bab 7', speech: '炒粿条', options: ['chǎo guǒ tiáo', 'cǎo guǒ tiáo', 'chāo kě tiáo', 'chào guó tiǎo'], answer: 'chǎo guǒ tiáo', explanation: '炒粿条 (chǎo guǒ tiáo) bermaksud kuetiau goreng.' },
      { type: 'mcq', emoji: '🍉', instruction: 'Pilih sebutan yang betul bagi "jus tembikai"', hanzi: '西瓜汁', pinyin: 'Kosa kata Bab 7', speech: '西瓜汁', options: ['xī guā zhī', 'qī guā zhī', 'xǐ kuā zī', 'sī guā chí'], answer: 'xī guā zhī', explanation: '西瓜汁 (xī guā zhī) bermaksud jus tembikai.' },
      { type: 'mcq', emoji: '💵', instruction: 'Apakah sebutan mata wang Malaysia "Ringgit" dan "Sen"?', hanzi: '令吉 / 仙', pinyin: 'Mata wang RM', speech: '令吉，仙', options: ['lìng jí / xiān', 'líng jǐ / qiān', 'lìng qí / shān', 'nìng jí / xiān'], answer: 'lìng jí / xiān', explanation: '三令吉五十仙 (sān lìngjí wǔshí xiān) = RM3.50.' }
    ],
    s3: [
      { type: 'mcq', emoji: '🍚', instruction: 'Radikal 饣 (radikal makanan) terdapat dalam perkataan...', hanzi: '饣', pinyin: 'shízìpáng', speech: '饭', options: ['饭、饿、饼、饱', '桌、校、杯、板', '喝、吃、叫、唱', '江、河、汁、汽'], answer: '饭、饿、饼、饱', explanation: '饭 (nasi), 饿 (lapar), 饼 (biskut/roti), dan 饱 (kenyang) beradikalkan 饣.' },
      { type: 'mcq', emoji: '👄', instruction: 'Radikal 口 (mulut) terdapat dalam kata kerja makan dan minum iaitu...', hanzi: '口', pinyin: 'kǒuzìpáng', speech: '吃喝', options: ['吃、喝、叫、唱', '粉、饭、饼、饱', '桌、校、杯、板', '爸、爷、妈、姐'], answer: '吃、喝、叫、唱', explanation: '吃 (makan), 喝 (minum), 叫 (memesan/panggil), dan 唱 (menyanyi) menggunakan radikal mulut 口.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 吃 (makan)?', hanzi: '吃', pinyin: 'chī', speech: '吃', options: ['5 goresan', '6 goresan', '7 goresan', '8 goresan'], answer: '6 goresan', explanation: 'Aksara 吃 mempunyai 6 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 杯 (cawan/gelas)?', hanzi: '杯', pinyin: 'bēi', speech: '杯', options: ['7 goresan', '8 goresan', '9 goresan', '10 goresan'], answer: '8 goresan', explanation: 'Aksara 杯 terdiri daripada 木 (4 goresan) + 不 (4 goresan) = 8 goresan.' },
      { type: 'mcq', emoji: '✍️', instruction: 'Berapakah bilangan goresan bagi aksara 喝 (minum)?', hanzi: '喝', pinyin: 'hē', speech: '喝', options: ['10 goresan', '11 goresan', '12 goresan', '13 goresan'], answer: '12 goresan', explanation: 'Aksara 喝 mempunyai 12 goresan.' }
    ],
    s4: [
      { type: 'mcq', emoji: '🔔', instruction: 'Pilih maksud perkataan ini', hanzi: '下课', pinyin: 'xià kè', speech: '下课', options: ['Waktu rehat / Tamat kelas', 'Mula kelas', 'Kerja rumah', 'Peperiksaan'], answer: 'Waktu rehat / Tamat kelas', explanation: '下课了 (Xiàkè le) bermaksud Sudah waktu rehat / tamat kelas.' },
      { type: 'mcq', emoji: '😋', instruction: 'Pilih maksud perasaan ini', hanzi: '饿', pinyin: 'è', speech: '饿', options: ['Lapar', 'Dahaga', 'Kenyang', 'Mengantuk'], answer: 'Lapar', explanation: '爸爸，我饿了 bermaksud Ayah, saya sudah lapar.' },
      { type: 'mcq', emoji: '🥗', instruction: 'Pilih maksud kata sifat bagi jus buah ini', hanzi: '新鲜', pinyin: 'xīn xiān', speech: '新鲜', options: ['Segar', 'Panas', 'Pedas', 'Manis'], answer: 'Segar', explanation: '这里的橙汁很新鲜 bermaksud jus oren di sini sangat segar.' },
      { type: 'mcq', emoji: '👨‍🍳', instruction: 'Pilih panggilan untuk pemilik kedai makan di kantin', hanzi: '老板', pinyin: 'lǎo bǎn', speech: '老板', options: ['Tauke / Pemilik kedai', 'Pelanggan', 'Pengetua', 'Pemandu'], answer: 'Tauke / Pemilik kedai', explanation: '老板 (lǎo bǎn) bermaksud tauke atau bos kedai.' },
      { type: 'mcq', emoji: '🫖', instruction: 'Pilih maksud minuman tradisi ini', hanzi: '中国茶', pinyin: 'zhōng guó chá', speech: '中国茶', options: ['Teh Cina', 'Teh tarik', 'Kopi ais', 'Air kelapa'], answer: 'Teh Cina', explanation: '中国茶 (zhōng guó chá) bermaksud teh Cina.' }
    ],
    s5: [
      { type: 'mcq', emoji: '🍜', instruction: 'Pilih penjodoh bilangan yang tepat untuk semangkuk mihun kari:', hanzi: '一____咖喱米粉', pinyin: 'Semangkuk mihun kari', speech: '一碗咖喱米粉', options: ['碗 (wǎn)', '盘 (pán)', '杯 (bēi)', '瓶 (píng)'], answer: '碗 (wǎn)', explanation: '碗 (wǎn) = mangkuk (untuk makanan berkuah seperti 咖喱米粉 / 咖喱面).' },
      { type: 'mcq', emoji: '🍗', instruction: 'Pilih penjodoh bilangan yang tepat untuk sepinggan nasi ayam:', hanzi: '弟弟吃了一____鸡饭。', pinyin: 'Sepinggan nasi ayam', speech: '弟弟吃了一盘鸡饭', options: ['盘 (pán)', '碗 (wǎn)', '杯 (bēi)', '串 (chuàn)'], answer: '盘 (pán)', explanation: '盘 (pán) = pinggan (untuk nasi goreng, mi goreng, dan nasi ayam).' },
      { type: 'mcq', emoji: '🥤', instruction: 'Pilih penjodoh bilangan yang tepat untuk segelas air berkarbonat:', hanzi: '一____汽水二令吉五十仙。', pinyin: 'Segelas/secawan minuman', speech: '一杯汽水', options: ['杯 (bēi)', '盘 (pán)', '碗 (wǎn)', '本 (běn)'], answer: '杯 (bēi)', explanation: '杯 (bēi) = cawan atau gelas; 瓶 (píng) = botol; 串 (chuàn) = cucuk (untuk sate).' },
      { type: 'mcq', emoji: '💵', instruction: 'RM3.50 disebut dalam bahasa Cina sebagai...', hanzi: 'RM 3.50', pinyin: 'Harga sepinggan nasi ayam', speech: '三令吉五十仙', options: ['三令吉五十仙', '三仙五十令吉', '三十五令吉', '三点五仙'], answer: '三令吉五十仙', explanation: 'RM3.50 = 三令吉五十仙 (sān lìngjí wǔshí xiān).' },
      { type: 'mcq', emoji: '🍽️', instruction: 'Untuk memesan makanan dengan sopan:', hanzi: '请____我叫一盘炒面。', pinyin: 'Tolong pesankan untuk saya', speech: '请帮我叫一盘炒面', options: ['帮 (bāng)', '给 (gěi)', '送 (sòng)', '借 (jiè)'], answer: '帮 (bāng)', explanation: '请帮我叫…… (Qǐng bāng wǒ jiào...) bermaksud Tolong pesankan untuk saya...' }
    ],
    s6: [
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Saya ingin makan nasi ayam Hainan', speech: '我想吃海南鸡饭', words: ['海南鸡饭', '我想', '吃'], answerOrder: ['我想', '吃', '海南鸡饭'], explanation: '我想 (Saya ingin) + 吃 (makan) + 海南鸡饭 (nasi ayam Hainan).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Saya mahu minum jus oren', speech: '我要喝橙汁', words: ['橙汁', '喝', '我要'], answerOrder: ['我要', '喝', '橙汁'], explanation: '我要 (Saya mahu) + 喝 (minum) + 橙汁 (jus oren).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Berapakah jumlah harganya?', speech: '一共多少钱', words: ['多少钱', '一共'], answerOrder: ['一共', '多少钱'], explanation: '一共 (Jumlah keseluruhan) + 多少钱 (berapa harga).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Awak suka makan apa?', speech: '你喜欢吃什么', words: ['什么', '吃', '你喜欢'], answerOrder: ['你喜欢', '吃', '什么'], explanation: '你喜欢 (Awak suka) + 吃 (makan) + 什么 (apa).' },
      { type: 'order', instruction: 'Susun perkataan menjadi ayat yang betul', promptText: 'Saya tidak suka minum kopi', speech: '我不喜欢喝咖啡', words: ['喝咖啡', '我不', '喜欢'], answerOrder: ['我不', '喜欢', '喝咖啡'], explanation: '我不喜欢 (Saya tidak suka) + 喝咖啡 (minum kopi).' }
    ],
    s7: [
      { type: 'comprehension', instruction: 'Baca dialog di kantin dan jawab soalan', passage: '丹尼买了一碗咖喱米粉（RM3.00）和一杯牛奶（RM1.00）。志平买了一盘鸡饭（RM3.50）和一杯橙汁（RM1.20）。', speech: '一碗咖喱米粉三令吉，一盘鸡饭三令吉五十仙。', question: 'Berapakah jumlah harga kesemua makanan dan minuman mereka?', options: ['RM8.70 (八令吉七十仙)', 'RM7.70 (七令吉七十仙)', 'RM8.50 (八令吉五十仙)', 'RM9.20 (九令吉二十仙)'], answer: 'RM8.70 (八令吉七十仙)', explanation: 'RM3.00 + RM1.00 + RM3.50 + RM1.20 = RM8.70 (八令吉七十仙).' },
      { type: 'comprehension', instruction: 'Baca dialog di medan selera dan jawab soalan', passage: '爸爸：我想吃炒粿条和沙爹，你呢？\n丽莎：请帮我叫一碗咖喱面和一个汉堡包，谢谢爸爸。', speech: '爸爸想吃炒粿条和沙爹，丽莎想吃咖喱面和汉堡包。', question: 'Apakah makanan yang dipesan oleh ayah Lisa?', options: ['Kuetiau goreng dan sate', 'Mi kari dan burger', 'Nasi lemak dan roti canai', 'Nasi ayam dan dim sum'], answer: 'Kuetiau goreng dan sate', explanation: '爸爸想吃炒粿条和沙爹 bermaksud ayah ingin makan kuetiau goreng dan sate.' },
      { type: 'comprehension', instruction: 'Baca senarai harga menu dan jawab soalan', passage: '餐厅菜单：椰浆饭 RM2.50，炒面 RM5.00，奶茶 RM1.80，白开水 RM0.50。', speech: '椰浆饭二令吉五十仙，奶茶一令吉八十仙。', question: 'Jika Aiman memesan sebungkus nasi lemak dan secawan teh tarik, berapakah bayarannya?', options: ['RM4.30 (四令吉三十仙)', 'RM5.50 (五令吉五十仙)', 'RM3.00 (三令吉)', 'RM6.80 (六令吉八十仙)'], answer: 'RM4.30 (四令吉三十仙)', explanation: '椰浆饭 (RM2.50) + 奶茶 (RM1.80) = RM4.30.' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '早上十点下课了，同学们一起去食堂吃早餐。美娜不喜欢喝咖啡，她喜欢喝牛奶，因为牛奶很有营养。', speech: '美娜不喜欢喝咖啡，她喜欢喝牛奶。', question: 'Mengapakah Meina suka minum susu?', options: ['Kerana susu berkhasiat (很有营养)', 'Kerana susu murah', 'Kerana tiada air kosong', 'Kerana susu pedas'], answer: 'Kerana susu berkhasiat (很有营养)', explanation: '因为牛奶很有营养 bermaksud kerana susu sangat berkhasiat.' },
      { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '中国茶文化历史悠久。泡中国茶时，我们要用热水，茶的味道很香。', speech: '泡中国茶时，我们要用热水，茶的味道很香。', question: 'Apakah jenis air yang digunakan untuk membancuh teh Cina?', options: ['Air panas (热水)', 'Air sejuk (冷水)', 'Air berkarbonat (汽水)', 'Jus tembikai (西瓜汁)'], answer: 'Air panas (热水)', explanation: '我们要用热水 bermaksud kita perlu menggunakan air panas.' }
    ]
  }
};