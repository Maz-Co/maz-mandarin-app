// =========================================================
// MAZ MANDARIN AKADEMIK — ENJIN PEMBELAJARAN & SILIBUS BCK
// Versi Stabil & Kalis Ralat (T1, T2, T3 + Lesen RM5)
// =========================================================

const STATIONS_META = [
  {
    id: 's1',
    step: 'Stesen 1 · Pengenalan',
    icon: '🃏',
    title: 'Kad Imbas Pintar (Flashcard)',
    desc: 'Kenali aksara Hanzi, gambar, bunyi Pinyin dan maksud BM.',
    isFlashcard: true
  },
  {
    id: 's2',
    step: 'Stesen 2 · Pengecaman Bunyi',
    icon: '🎧',
    title: 'Pinyin & 4 Nada (Ting Li)',
    desc: 'Dengar sebutan dan kenal pasti Pinyin serta nada yang tepat.'
  },
  {
    id: 's3',
    step: 'Stesen 3 · Struktur Tulisan',
    icon: '🧩',
    title: 'Radikal & Goresan (Hanzi)',
    desc: 'Fahami komponen asas radikal dan urutan goresan aksara.'
  },
  {
    id: 's4',
    step: 'Stesen 4 · Pengukuhan Makna',
    icon: '📖',
    title: 'Padanan Kosa Kata (Shengci)',
    desc: 'Uji kefahaman maksud perkataan teras dalam Bahasa Melayu.'
  },
  {
    id: 's5',
    step: 'Stesen 5 · Aplikasi Lisan',
    icon: '💬',
    title: 'Melengkapkan Dialog (Duihua)',
    desc: 'Pilih respons perbualan harian yang paling sopan dan tepat.'
  },
  {
    id: 's6',
    step: 'Stesen 6 · Sintaksis & Tatabahasa',
    icon: '🧱',
    title: 'Susun Ayat Gramatis',
    desc: 'Susun blok perkataan mengikut susunan ayat Mandarin.'
  },
  {
    id: 's7',
    step: 'Stesen 7 · Penilaian Akhir',
    icon: '🧠',
    title: 'Pemahaman Petikan (Yuedu)',
    desc: 'Baca petikan pendek bersepadu dan jawab soalan pemahaman.'
  }
];

const SYLLABUS_CHAPTERS = {
  t1: {
    label: 'Tingkatan 1',
    short: 'T1',
    chapters: [
      { id: 'c1', num: 'Bab 1', emoji: '👋', title: '你好 (Apa Khabar)', hanzi: '第一课 · 你好', desc: 'Ucapan sapaan, memperkenalkan diri, ucapan harian & goresan asas.' },
      { id: 'c2', num: 'Bab 2', emoji: '🎂', title: '我的生日 (Hari Lahir Saya)', hanzi: '第二课 · 我的生日', desc: 'Nombor 0–999, tarikh, hari dalam seminggu & menyatakan masa.' },
      { id: 'c3', num: 'Bab 3', emoji: '🏃', title: '我的身体 (Badan Saya)', hanzi: '第三课 · 我的身体', desc: 'Anggota badan, jari-jemari, pancaindera & teka-teki.' },
      { id: 'c4', num: 'Bab 4', emoji: '👨‍👩‍👧‍👦', title: '我的家人 (Keluarga Saya)', hanzi: '第四课 · 我的家人', desc: 'Panggilan ahli keluarga besar, umur & salasilah keluarga.' },
      { id: 'c5', num: 'Bab 5', emoji: '🏫', title: '课室里 (Di Dalam Kelas)', hanzi: '第五课 · 课室里', desc: 'Peralatan bilik darjah, alat tulis, penjodoh bilangan & arahan.' },
      { id: 'c6', num: 'Bab 6', emoji: '🏢', title: '我的学校 (Sekolah Saya)', hanzi: '第六课 · 我的学校', desc: 'Kemudahan di sekolah, kata arah (kiri/kanan/depan/belakang).' },
      { id: 'c7', num: 'Bab 7', emoji: '🍜', title: '去食堂 (Ke Kantin)', hanzi: '第七课 · 去食堂', desc: 'Memesan makanan & minuman, harga RM & penjodoh bilangan.' }
    ]
  },
  t2: {
    label: 'Tingkatan 2',
    short: 'T2',
    chapters: [
      { id: 'c1', num: 'Bab 1', emoji: '🏸', title: '李端的一天与爱好', hanzi: '单元一 · Rutin & Hobi', desc: 'Jadual harian Li Duan, aktiviti sukan, hobi dan masa lapang.' },
      { id: 'c2', num: 'Bab 2', emoji: '✈️', title: '假期与交通工具', hanzi: '单元二 · Cuti & Pengangkutan', desc: 'Diari percutian Meina, cuaca dan pengangkutan darat, udara & laut.' },
      { id: 'c3', num: 'Bab 3', emoji: '🥭', title: '本地与外国水果', hanzi: '单元三 · Buah-buahan', desc: 'Buah-buahan tempatan Malaysia, buah import, harga & perbandingan.' },
      { id: 'c4', num: 'Bab 4', emoji: '🤒', title: '健康与看医生', hanzi: '单元四 · Kesihatan & Doktor', desc: 'Simptom penyakit, berjumpa doktor, e-mel cuti sakit & penjagaan diri.' },
      { id: 'c5', num: 'Bab 5', emoji: '🥬', title: '菜市与美食餐馆', hanzi: '单元五 · Pasar & Restoran', desc: 'Sayur-sayuran, lauk di pasar basah dan memesan hidangan pelbagai kaum.' },
      { id: 'c6', num: 'Bab 6', emoji: '🦏', title: '动物园与人物外貌', hanzi: '单元六 · Zoo & Rupa Paras', desc: 'Haiwan dilindungi di Zoo Negara (Tapir Malaysia) & pola ayat 又……又……' },
      { id: 'c7', num: 'Bab 7', emoji: '🇲🇾', title: '志愿与多元马来西亚', hanzi: '单元七至八 · Cita-cita & Negara', desc: 'Kerjaya impian Hisham serta kepelbagaian kaum, perayaan & makanan Malaysia.' }
    ]
  },
  t3: {
    label: 'Tingkatan 3',
    short: 'T3',
    chapters: [
      { id: 'c1', num: 'Bab 1', emoji: '🏠', title: '姑姑的新房子', hanzi: '单元一 · Rumah Baharu', desc: 'Bahagian rumah, perabot, adab melayan tetamu & pola ayat 一边……一边……' },
      { id: 'c2', num: 'Bab 2', emoji: '🐱', title: '学习关怀', hanzi: '单元二 · Prihatin & Kasih', desc: 'Menyayangi haiwan, sokongan menghadapi peperiksaan & pola 只要……就……' },
      { id: 'c3', num: 'Bab 3', emoji: '🏆', title: '学校运动会', hanzi: '单元三 · Sukan Sekolah', desc: 'Acara balapan & padang, semangat kesukanan & notis rasmi sekolah.' },
      { id: 'c4', num: 'Bab 4', emoji: '🛍️', title: '去购物与逛书店', hanzi: '单元四至五 · Beli-belah & Buku', desc: 'Bahagian di pusat beli-belah, kedai buku, penjodoh bilangan & alat tulis.' },
      { id: 'c5', num: 'Bab 5', emoji: '🏛️', title: '爱护公物，人人有责', hanzi: '单元六 · Kemudahan Awam', desc: 'Etika di tempat awam, menjaga kebersihan & perubahan nada ke-3.' },
      { id: 'c6', num: 'Bab 6', emoji: '🥮', title: '华人的传统节日', hanzi: '单元七 · Perayaan Tradisional', desc: 'Pesta Kuih Bulan, Dongzhi, Chap Goh Mei & adat tradisi masyarakat Cina.' },
      { id: 'c7', num: 'Bab 7', emoji: '🏔️', title: '我爱旅游', hanzi: '单元八 · Pelancongan Malaysia', desc: 'Gunung Kinabalu, Bunga Rafflesia, mercu tanda KL & destinasi Malaysia.' }
    ]
  }
};

const FALLBACK_STATION_CONTENT = {
  s1: [
    { emoji: '👋', hanzi: '你好', pinyin: 'nǐ hǎo', meaning: 'Apa khabar / Helo', exCn: '同学，你好！', exPy: 'Tóngxué, nǐ hǎo!', exBm: 'Apa khabar, rakan sekelas!' },
    { emoji: '👩‍🏫', hanzi: '老师', pinyin: 'lǎo shī', meaning: 'Guru / Cikgu', exCn: '王老师早安！', exPy: 'Wáng lǎoshī zǎo’ān!', exBm: 'Selamat pagi, Cikgu Wang!' },
    { emoji: '🏫', hanzi: '学校', pinyin: 'xué xiào', meaning: 'Sekolah', exCn: '我去学校上课。', exPy: 'Wǒ qù xuéxiào shàngkè.', exBm: 'Saya pergi ke sekolah untuk belajar.' },
    { emoji: '🥛', hanzi: '牛奶', pinyin: 'niú nǎi', meaning: 'Susu', exCn: '我每天早上喝牛奶。', exPy: 'Wǒ měitiān zǎoshang hē niúnǎi.', exBm: 'Saya minum susu setiap pagi.' },
    { emoji: '🙏', hanzi: '谢谢', pinyin: 'xiè xie', meaning: 'Terima kasih', exCn: '谢谢老师的教导！', exPy: 'Xièxie lǎoshī de jiàodǎo!', exBm: 'Terima kasih atas tunjuk ajar cikgu!' }
  ],
  s2: [
    { type: 'mcq', emoji: '👩', instruction: 'Pilih maksud nada pertama (mā)', hanzi: '妈', pinyin: 'mā', speech: '妈', options: ['Ibu', 'Kuda', 'Kebas', 'Marah'], answer: 'Ibu', explanation: '妈 (mā) bermaksud Ibu.' },
    { type: 'mcq', emoji: '👍', instruction: 'Pilih ejaan Pinyin yang tepat', hanzi: '好', pinyin: 'Nada ke-3', speech: '好', options: ['hāo', 'háo', 'hǎo', 'hào'], answer: 'hǎo', explanation: '好 dibaca hǎo bermaksud baik.' },
    { type: 'mcq', emoji: '🌅', instruction: 'Apakah bunyi suku kata ini?', hanzi: '早', pinyin: 'z + ǎo', speech: '早', options: ['zǎo', 'wǎn', 'hǎo', 'lǎo'], answer: 'zǎo', explanation: '早 dibaca zǎo bermaksud pagi.' },
    { type: 'mcq', emoji: '1️⃣', instruction: 'Kenali sebutan bagi nombor satu', hanzi: '一', pinyin: 'Nada 1', speech: '一', options: ['yī', 'èr', 'sān', 'sì'], answer: 'yī', explanation: '一 dibaca yī bermaksud nombor satu.' },
    { type: 'mcq', emoji: '🏠', instruction: 'Pilih sebutan tepat bagi rumah/keluarga', hanzi: '家', pinyin: 'j + iā', speech: '家', options: ['jiā', 'xià', 'qià', 'jiè'], answer: 'jiā', explanation: '家 dibaca jiā bermaksud rumah atau keluarga.' }
  ],
  s3: [
    { type: 'mcq', emoji: '🧍', instruction: 'Apakah maksud radikal 亻?', hanzi: '亻', pinyin: 'dānrénpáng', speech: '人', options: ['Orang / Manusia', 'Air', 'Api', 'Kayu'], answer: 'Orang / Manusia', explanation: 'Radikal 亻 berasal daripada 人 (rén).' },
    { type: 'mcq', emoji: '💧', instruction: 'Radikal 氵 merujuk kepada...', hanzi: '氵', pinyin: 'sāndiǎnshuǐ', speech: '水', options: ['Cecair / Air', 'Makanan', 'Logam', 'Tanah'], answer: 'Cecair / Air', explanation: '氵 ialah radikal air.' },
    { type: 'mcq', emoji: '➕', instruction: 'Berapakah bilangan goresan bagi aksara 十?', hanzi: '十', pinyin: 'shí', speech: '十', options: ['1 goresan', '2 goresan', '3 goresan', '4 goresan'], answer: '2 goresan', explanation: '十 ditulis dengan 2 goresan.' },
    { type: 'mcq', emoji: '👄', instruction: 'Peraturan menulis aksara 口:', hanzi: '口', pinyin: 'kǒu', speech: '口', options: ['Tutup garis bawah paling akhir', 'Tutup garis bawah dahulu', 'Tulis dari bawah ke atas', 'Tulis dari kanan ke kiri'], answer: 'Tutup garis bawah paling akhir', explanation: 'Garis penutup bawah ditulis paling akhir.' },
    { type: 'mcq', emoji: '✨', instruction: 'Gabungan 女 + 子 membentuk...', hanzi: '女 + 子', pinyin: 'nǚ + zǐ', speech: '好', options: ['好', '妈', '姐', '妹'], answer: '好', explanation: '女 + 子 membentuk 好 (hǎo).' }
  ],
  s4: [
    { type: 'mcq', emoji: '👩‍🏫', instruction: 'Pilih maksud perkataan ini', hanzi: '老师', pinyin: 'lǎoshī', speech: '老师', options: ['Murid', 'Guru', 'Pengetua', 'Rakan'], answer: 'Guru', explanation: '老师 (lǎoshī) bermaksud Guru.' },
    { type: 'mcq', emoji: '🧑‍🎓', instruction: 'Pilih maksud perkataan ini', hanzi: '同学', pinyin: 'tóngxué', speech: '同学', options: ['Rakan sekelas', 'Adik lelaki', 'Jiran', 'Doktor'], answer: 'Rakan sekelas', explanation: '同学 (tóngxué) bermaksud Rakan sekelas.' },
    { type: 'mcq', emoji: '🏫', instruction: 'Pilih maksud perkataan ini', hanzi: '学校', pinyin: 'xuéxiào', speech: '学校', options: ['Perpustakaan', 'Kantin', 'Sekolah', 'Padang'], answer: 'Sekolah', explanation: '学校 (xuéxiào) bermaksud Sekolah.' },
    { type: 'mcq', emoji: '👋', instruction: 'Pilih maksud ucapan ini', hanzi: '再见', pinyin: 'zàijiàn', speech: '再见', options: ['Selamat pagi', 'Terima kasih', 'Jumpa lagi', 'Maafkan saya'], answer: 'Jumpa lagi', explanation: '再见 (zàijiàn) bermaksud Jumpa lagi.' },
    { type: 'mcq', emoji: '🥛', instruction: 'Pilih maksud minuman ini', hanzi: '牛奶', pinyin: 'niúnǎi', speech: '牛奶', options: ['Kopi', 'Teh', 'Susu', 'Air kosong'], answer: 'Susu', explanation: '牛奶 (niúnǎi) bermaksud Susu.' }
  ],
  s5: [
    { type: 'mcq', emoji: '💬', instruction: 'A: 你好！ — Apakah balasan B?', hanzi: 'B: ________！', pinyin: 'Sapaan sopan', speech: '你好', options: ['你好 (Nǐ hǎo)', '再见 (Zàijiàn)', '对不起 (Duìbuqǐ)', '谢谢 (Xièxie)'], answer: '你好 (Nǐ hǎo)', explanation: 'Balasan bagi 你好 ialah 你好!' },
    { type: 'mcq', emoji: '🎁', instruction: 'A: 谢谢你！ — Apakah jawapan sopan B?', hanzi: 'B: ________！', pinyin: 'Sama-sama', speech: '不客气', options: ['不客气 (Bú kèqi)', '早安 (Zǎo’ān)', '再见 (Zàijiàn)', '你好吗 (Nǐ hǎo ma)'], answer: '不客气 (Bú kèqi)', explanation: '不客气 bermaksud Sama-sama.' },
    { type: 'mcq', emoji: '🪪', instruction: 'A: 你叫什么名字？', hanzi: 'B: 我____阿里。', pinyin: 'Nama saya Ali', speech: '我叫阿里', options: ['叫 (jiào)', '去 (qù)', '喝 (hē)', '有 (yǒu)'], answer: '叫 (jiào)', explanation: '我叫阿里 bermaksud Nama saya Ali.' },
    { type: 'mcq', emoji: '🏫', instruction: 'A: 你去哪里？', hanzi: 'B: 我去____。', pinyin: 'Saya pergi ke sekolah', speech: '我去学校', options: ['学校 (xuéxiào)', '牛奶 (niúnǎi)', '老师 (lǎoshī)', '名字 (míngzi)'], answer: '学校 (xuéxiào)', explanation: '我去学校 bermaksud Saya pergi ke sekolah.' },
    { type: 'mcq', emoji: '🌅', instruction: 'Ucapan pagi kepada guru:', hanzi: '老师，____！', pinyin: 'Selamat pagi, cikgu!', speech: '老师早安', options: ['早安 (zǎo’ān)', '晚安 (wǎn’ān)', '再见 (zàijiàn)', '谢谢 (xièxie)'], answer: '早安 (zǎo’ān)', explanation: '老师早安 bermaksud Selamat pagi, cikgu.' }
  ],
  s6: [
    { type: 'order', instruction: 'Susun perkataan menjadi ayat', promptText: 'Saya suka susu', speech: '我喜欢牛奶', words: ['喜欢', '牛奶', '我'], answerOrder: ['我', '喜欢', '牛奶'], explanation: '我 + 喜欢 + 牛奶.' },
    { type: 'order', instruction: 'Susun perkataan menjadi ayat', promptText: 'Nama saya Ali', speech: '我叫阿里', words: ['阿里', '我', '叫'], answerOrder: ['我', '叫', '阿里'], explanation: '我 + 叫 + 阿里.' },
    { type: 'order', instruction: 'Susun perkataan menjadi ayat', promptText: 'Selamat pagi, cikgu', speech: '老师早安', words: ['早安', '老师'], answerOrder: ['老师', '早安'], explanation: '老师 + 早安.' },
    { type: 'order', instruction: 'Susun perkataan menjadi ayat', promptText: 'Dia rakan sekelas saya', speech: '他是我的同学', words: ['我的', '他', '同学', '是'], answerOrder: ['他', '是', '我的', '同学'], explanation: '他 + 是 + 我的 + 同学.' },
    { type: 'order', instruction: 'Susun perkataan menjadi ayat', promptText: 'Saya pergi ke sekolah', speech: '我去学校', words: ['学校', '去', '我'], answerOrder: ['我', '去', '学校'], explanation: '我 + 去 + 学校.' }
  ],
  s7: [
    { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '我喜欢牛奶。妈妈有牛奶。', speech: '我喜欢牛奶。妈妈有牛奶。', question: 'Apakah minuman yang disukai?', options: ['Kopi', 'Teh', 'Susu', 'Jus oren'], answer: 'Susu', explanation: '我喜欢牛奶 bermaksud Saya suka susu.' },
    { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '你好！我叫茜蒂。我是中一的学生。', speech: '你好！我叫茜蒂。我是中一的学生。', question: 'Siti murid tingkatan berapa?', options: ['Tingkatan 1', 'Tingkatan 2', 'Tingkatan 3', 'Tingkatan 4'], answer: 'Tingkatan 1', explanation: '中一 bermaksud Tingkatan 1.' },
    { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '今天星期一。我和哥哥去学校。', speech: '今天星期一。我和哥哥去学校。', question: 'Dengan siapakah dia pergi ke sekolah?', options: ['Abang', 'Kakak', 'Ayah', 'Adik'], answer: 'Abang', explanation: '哥哥 bermaksud abang.' },
    { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '我家有五个人：爸爸、妈妈、哥哥、妹妹和我。', speech: '我家有五个人。', question: 'Berapakah ahli keluarga di dalam petikan?', options: ['3 orang', '4 orang', '5 orang', '6 orang'], answer: '5 orang', explanation: '五个人 bermaksud 5 orang.' },
    { type: 'comprehension', instruction: 'Baca petikan dan jawab soalan', passage: '王老师在课室里。同学们说：“老师好！”', speech: '王老师在课室里。', question: 'Di manakah Cikgu Wang berada?', options: ['Di dalam bilik darjah', 'Di kantin', 'Di padang', 'Di pejabat'], answer: 'Di dalam bilik darjah', explanation: '在课室里 bermaksud di dalam bilik darjah.' }
  ]
};

// =========================================================
// SISTEM LESEN / KOD AKSES JUALAN (RM5)
// =========================================================
const VALID_LICENSE_KEYS = [
  'MAZ2026',
  'BCKPRO5',
  'BCKPRO15',
  'MANDARIN88',
  'SEMESRA2026',
  'VIPMANDARIN'
];
const LICENSE_STORAGE_KEY = 'maz_mandarin_unlocked_v1';

function isAppUnlocked() {
  try {
    return localStorage.getItem(LICENSE_STORAGE_KEY) === 'true';
  } catch (e) {
    return false;
  }
}

function unlockFullAccess() {
  try {
    localStorage.setItem(LICENSE_STORAGE_KEY, 'true');
  } catch (e) {}
  const modal = document.getElementById('modalLicense');
  if (modal) modal.classList.add('hidden');
  renderChaptersGrid();
  alert('🎉 Tahniah! Akses Penuh MAZ MANDARIN AKADEMIK (Tingkatan 1–3) telah berjaya diaktifkan!');
}

function getActiveStationItems(stationId) {
  const lvl = appState.currentLevel;
  const chap = appState.currentChapterId;

  if (lvl === 't1' && window.DATA_T1 && window.DATA_T1[chap] && window.DATA_T1[chap][stationId]) {
    return window.DATA_T1[chap][stationId];
  }
  if (lvl === 't2' && window.DATA_T2 && window.DATA_T2[chap] && window.DATA_T2[chap][stationId]) {
    return window.DATA_T2[chap][stationId];
  }
  if (lvl === 't3' && window.DATA_T3 && window.DATA_T3[chap] && window.DATA_T3[chap][stationId]) {
    return window.DATA_T3[chap][stationId];
  }
  return FALLBACK_STATION_CONTENT[stationId];
}

// =========================================================
// ENJIN FAIL AUDIO TEMPATAN (betul.m4a & salah.m4a)
// =========================================================
let sfxStopTimer = null;

function stopAllLocalSfx() {
  clearTimeout(sfxStopTimer);
  const correctEl = document.getElementById('audioSfxCorrect');
  const wrongEl = document.getElementById('audioSfxWrong');
  if (correctEl) {
    correctEl.pause();
    correctEl.currentTime = 0;
  }
  if (wrongEl) {
    wrongEl.pause();
    wrongEl.currentTime = 0;
  }
}

function playKidCheeringSfx(followUpMandarin) {
  if (!appState.settings.soundFx) {
    if (followUpMandarin) speakMandarin(followUpMandarin);
    return;
  }

  stopAllLocalSfx();
  const correctEl = document.getElementById('audioSfxCorrect');

  const runMandarinAfter = () => {
    if (followUpMandarin) {
      speakMandarin(followUpMandarin);
    }
  };

  if (correctEl) {
    correctEl.currentTime = 0;
    correctEl.play().then(() => {
      sfxStopTimer = setTimeout(() => {
        correctEl.pause();
        correctEl.currentTime = 0;
        runMandarinAfter();
      }, 4000);

      correctEl.onended = () => {
        clearTimeout(sfxStopTimer);
        runMandarinAfter();
      };
    }).catch(() => {
      runMandarinAfter();
    });
  } else {
    runMandarinAfter();
  }
}

function playKidWrongSfx() {
  if (!appState.settings.soundFx) return;

  stopAllLocalSfx();
  const wrongEl = document.getElementById('audioSfxWrong');

  if (wrongEl) {
    wrongEl.currentTime = 0;
    wrongEl.play().then(() => {
      sfxStopTimer = setTimeout(() => {
        wrongEl.pause();
        wrongEl.currentTime = 0;
      }, 4000);
    }).catch(() => {});
  }
}

// =========================================================
// ENJIN SUARA MANDARIN NATURAL
// =========================================================
let availableMandarinVoices = [];

function populateMandarinVoices() {
  if (!('speechSynthesis' in window)) return;
  const allVoices = window.speechSynthesis.getVoices();
  availableMandarinVoices = allVoices.filter((v) =>
    v.lang.toLowerCase().includes('zh') || v.lang.toLowerCase().includes('cmn')
  );

  availableMandarinVoices.sort((a, b) => {
    const score = (v) => {
      const name = v.name.toLowerCase();
      let s = 0;
      if (name.includes('natural') || name.includes('neural')) s += 50;
      if (name.includes('xiaoxiao') || name.includes('yunxi') || name.includes('xiaoyi')) s += 40;
      if (name.includes('google') && name.includes('普通话')) s += 35;
      if (name.includes('tingting') || name.includes('meijia') || name.includes('sinji')) s += 30;
      if (v.lang === 'zh-CN' || v.lang === 'zh_CN') s += 15;
      if (!v.localService) s += 10;
      return s;
    };
    return score(b) - score(a);
  });

  const selectEl = document.getElementById('selectMandarinVoice');
  if (!selectEl) return;
  selectEl.innerHTML = '';

  if (availableMandarinVoices.length === 0) {
    const opt = document.createElement('option');
    opt.value = '';
    opt.textContent = 'Suara Mandarin Lalai Pelayar (zh-CN)';
    selectEl.appendChild(opt);
    return;
  }

  availableMandarinVoices.forEach((v, idx) => {
    const opt = document.createElement('option');
    opt.value = v.name;
    const isNeural = v.name.toLowerCase().includes('natural') || v.name.toLowerCase().includes('neural');
    opt.textContent = `${idx === 0 ? '⭐ ' : ''}${v.name} (${v.lang})${isNeural ? ' — Natural' : ''}`;
    selectEl.appendChild(opt);
  });

  if (appState.settings.preferredVoice) {
    selectEl.value = appState.settings.preferredVoice;
  } else if (availableMandarinVoices[0]) {
    appState.settings.preferredVoice = availableMandarinVoices[0].name;
    selectEl.value = availableMandarinVoices[0].name;
  }
}

if ('speechSynthesis' in window) {
  populateMandarinVoices();
  window.speechSynthesis.onvoiceschanged = populateMandarinVoices;
}

function speakMandarin(text) {
  if (!('speechSynthesis' in window)) return;
  window.speechSynthesis.cancel();

  const naturalText = text.replace(/。/g, '， ');
  const utter = new SpeechSynthesisUtterance(naturalText);
  utter.lang = 'zh-CN';

  if (availableMandarinVoices.length === 0) {
    populateMandarinVoices();
  }
  const chosenVoice =
    availableMandarinVoices.find((v) => v.name === appState.settings.preferredVoice) ||
    availableMandarinVoices[0];

  if (chosenVoice) {
    utter.voice = chosenVoice;
  }

  utter.rate = 0.88;
  utter.pitch = 1.04;
  window.speechSynthesis.speak(utter);
}

// =========================================================
// STATE & STORAGE MANAGEMENT
// =========================================================
const STORAGE_KEY = 'maz_mandarin_akademik_v10';

let appState = {
  currentLevel: 't1',
  currentChapterId: 'c1',
  currentStationId: 's1',
  currentItemIndex: 0,
  selectedOrderWords: [],
  lastActivity: null,
  settings: {
    showPinyin: true,
    soundFx: true,
    autoSpeak: false,
    preferredVoice: ''
  },
  progress: {
    t1: { stars: 0, completed: {} },
    t2: { stars: 0, completed: {} },
    t3: { stars: 0, completed: {} }
  }
};

function loadState() {
  try {
    const saved = localStorage.getItem(STORAGE_KEY);
    if (saved) {
      const parsed = JSON.parse(saved);
      appState = {
        ...appState,
        ...parsed,
        settings: { ...appState.settings, ...(parsed.settings || {}) }
      };
    }
  } catch (e) {
    console.warn('Gagal memuatkan simpanan:', e);
  }
}

function saveState() {
  try {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(appState));
  } catch (e) {
    console.warn('Gagal menyimpan kemajuan:', e);
  }
}

function recordLastActivity(level, chapterId, stationId, itemIndex) {
  appState.lastActivity = { level, chapterId, stationId, itemIndex };
  saveState();
}

// =========================================================
// NAVIGATION: BOTTOM DOCK & SUB-VIEWS
// =========================================================
function switchMainTab(tabId) {
  stopAllLocalSfx();
  document.querySelectorAll('.tab-view').forEach((v) => v.classList.add('hidden'));
  const targetTab = document.getElementById(tabId);
  if (targetTab) targetTab.classList.remove('hidden');

  document.querySelectorAll('.dock-item').forEach((btn) => {
    btn.classList.toggle('active', btn.dataset.tab === tabId);
  });

  if (tabId === 'viewAcademy') {
    showAcademySubView('subViewChapters');
    renderChaptersGrid();
  } else if (tabId === 'viewProgress') {
    renderProgressTab();
  }
  window.scrollTo({ top: 0, behavior: 'smooth' });
}

function showAcademySubView(subId) {
  stopAllLocalSfx();
  ['subViewChapters', 'subViewStations', 'subViewArena'].forEach((id) => {
    const el = document.getElementById(id);
    if (el) el.classList.add('hidden');
  });
  const targetSub = document.getElementById(subId);
  if (targetSub) targetSub.classList.remove('hidden');
  window.scrollTo({ top: 0, behavior: 'smooth' });
}

// =========================================================
// SUB-VIEW A: GRID 7 BAB & JEJAK LATIHAN TERAKHIR
// =========================================================
function renderChaptersGrid() {
  const lvl = SYLLABUS_CHAPTERS[appState.currentLevel] ? appState.currentLevel : 't1';
  appState.currentLevel = lvl;
  const lvlData = SYLLABUS_CHAPTERS[lvl];
  const lvlProgress = appState.progress[lvl] || { stars: 0, completed: {} };
  const unlocked = isAppUnlocked();

  const starsEl = document.getElementById('totalStarsDisplay');
  const badgeEl = document.getElementById('headerLevelBadge');
  if (starsEl) starsEl.textContent = lvlProgress.stars;
  if (badgeEl) badgeEl.textContent = lvlData.short;

  document.querySelectorAll('.seg-btn').forEach((btn) => {
    const isSel = btn.dataset.level === lvl;
    btn.classList.toggle('active', isSel);
    btn.setAttribute('aria-selected', isSel);
  });

  const titleEl = document.getElementById('lastActivityTitle');
  const subEl = document.getElementById('lastActivitySub');
  const resumeBtn = document.getElementById('btnResumeLast');

  if (appState.lastActivity && SYLLABUS_CHAPTERS[appState.lastActivity.level] && titleEl && subEl && resumeBtn) {
    const act = appState.lastActivity;
    const actLvl = SYLLABUS_CHAPTERS[act.level];
    const actChap = actLvl.chapters.find((c) => c.id === act.chapterId) || actLvl.chapters[0];
    const actStation = STATIONS_META.find((s) => s.id === act.stationId) || STATIONS_META[0];

    titleEl.textContent = `${actLvl.label} · ${actChap.num}: ${actChap.title}`;
    subEl.textContent = `Terakhir di: ${actStation.icon} ${actStation.title}`;
    resumeBtn.textContent = 'Teruskan ▶';
    resumeBtn.onclick = () => {
      appState.currentLevel = act.level;
      appState.currentChapterId = act.chapterId;
      openStation(act.stationId, act.itemIndex || 0);
    };
  } else if (titleEl && subEl && resumeBtn) {
    titleEl.textContent = `${lvlData.label} · Bab 1: ${lvlData.chapters[0].title}`;
    subEl.textContent = 'Mula dengan Stesen 1: Kad Imbas Pintar (Flashcard)';
    resumeBtn.textContent = 'Mula ▶';
    resumeBtn.onclick = () => {
      appState.currentChapterId = 'c1';
      openStation('s1', 0);
    };
  }

  const totalDone = Object.keys(lvlProgress.completed).length;
  const summaryEl = document.getElementById('chapterSummaryText');
  if (summaryEl) summaryEl.textContent = `${totalDone}/49 Stesen`;

  const grid = document.getElementById('chapterGridContainer');
  if (!grid) return;
  grid.innerHTML = '';

  lvlData.chapters.forEach((chap, idx) => {
    let doneCount = 0;
    STATIONS_META.forEach((st) => {
      if (lvlProgress.completed[`${chap.id}_${st.id}`]) doneCount++;
    });
    const pct = (doneCount / 7) * 100;
    const isDone = doneCount === 7;
    const isLocked = idx > 0 && !unlocked;

    const tile = document.createElement('div');
    tile.className = `chap-tile-3d ${isDone ? 'done' : ''} ${isLocked ? 'locked' : ''}`;
    tile.innerHTML = `
      <div>
        <div class="chap-tile-top">
          <span class="chap-num-tag">${chap.num}${idx === 0 ? ' · PERCUMA' : ''}</span>
          <span class="chap-emoji">${chap.emoji}</span>
        </div>
        <div class="chap-tile-title">${chap.title}</div>
        <div class="chap-tile-hanzi">${chap.hanzi}</div>
      </div>
      <div class="chap-tile-footer">
        <div class="mini-bar"><div class="mini-bar-fill" style="width: ${pct}%"></div></div>
        <span class="chap-stat-txt">${isLocked ? '🔒 Terkunci' : `${doneCount}/7`}</span>
      </div>
    `;
    tile.addEventListener('click', () => openChapterStations(chap.id));
    grid.appendChild(tile);
  });
}

// =========================================================
// SUB-VIEW B: 7 STESEN PEDAGOGI MENGIKUT TERTIB
// =========================================================
function openChapterStations(chapterId) {
  if (chapterId !== 'c1' && !isAppUnlocked()) {
    const errEl = document.getElementById('licenseErrorMsg');
    const inpEl = document.getElementById('inputLicenseKey');
    const modalEl = document.getElementById('modalLicense');
    if (errEl) errEl.classList.add('hidden');
    if (inpEl) inpEl.value = '';
    if (modalEl) modalEl.classList.remove('hidden');
    return;
  }

  appState.currentChapterId = chapterId;
  const lvl = appState.currentLevel;
  const lvlData = SYLLABUS_CHAPTERS[lvl];
  const chapter = lvlData.chapters.find((c) => c.id === chapterId);
  const lvlProgress = appState.progress[lvl];

  let doneCount = 0;
  STATIONS_META.forEach((st) => {
    if (lvlProgress.completed[`${chapterId}_${st.id}`]) doneCount++;
  });

  document.getElementById('selectedChapterProgress').textContent = `${doneCount}/7 Selesai`;
  document.getElementById('chapterBannerLevel').textContent = `${lvlData.label.toUpperCase()} · ${chapter.num.toUpperCase()}`;
  document.getElementById('chapterBannerTitle').textContent = `${chapter.emoji} ${chapter.title}`;
  document.getElementById('chapterBannerDesc').textContent = chapter.desc;

  const list = document.getElementById('stationListContainer');
  list.innerHTML = '';

  STATIONS_META.forEach((st) => {
    const isDone = Boolean(lvlProgress.completed[`${chapterId}_${st.id}`]);
    const node = document.createElement('div');
    node.className = `station-node-3d ${st.isFlashcard ? 'flashcard-highlight' : ''} ${isDone ? 'completed' : ''}`;
    node.innerHTML = `
      <div class="station-step-badge">${st.icon}</div>
      <div class="station-info">
        <span class="station-order-tag">${st.step}</span>
        <div class="station-title">${st.title}</div>
        <div class="station-desc">${st.desc}</div>
      </div>
      <div class="station-action-pill">${isDone ? '⭐ Ulang' : 'Mula →'}</div>
    `;
    node.addEventListener('click', () => openStation(st.id, 0));
    list.appendChild(node);
  });

  showAcademySubView('subViewStations');
}

// =========================================================
// SUB-VIEW C: ARENA INTERAKTIF (FLASHCARD & KUIZ)
// =========================================================
function openStation(stationId, startIndex = 0) {
  appState.currentStationId = stationId;
  appState.currentItemIndex = startIndex;
  recordLastActivity(appState.currentLevel, appState.currentChapterId, stationId, startIndex);

  const stMeta = STATIONS_META.find((s) => s.id === stationId);
  document.getElementById('arenaHeaderTitle').textContent = `${stMeta.icon} ${stMeta.title}`;

  if (stMeta.isFlashcard) {
    document.getElementById('flashcardStage').classList.remove('hidden');
    document.getElementById('quizStage').classList.add('hidden');
    renderFlashcard();
  } else {
    document.getElementById('flashcardStage').classList.add('hidden');
    document.getElementById('quizStage').classList.remove('hidden');
    renderQuizQuestion();
  }

  showAcademySubView('subViewArena');
}

// --- MOD 1: FLASHCARD BERGAMBAR 3D ---
function renderFlashcard() {
  const cards = getActiveStationItems('s1');
  const idx = appState.currentItemIndex;
  const card = cards[idx];
  const num = idx + 1;

  recordLastActivity(appState.currentLevel, appState.currentChapterId, 's1', idx);

  document.getElementById('arenaCounter').textContent = `${num}/${cards.length}`;
  document.getElementById('arenaProgressFill').style.width = `${(num / cards.length) * 100}%`;

  document.getElementById('flashcardInner').classList.remove('flipped');

  document.getElementById('fcEmoji').textContent = card.emoji;
  document.getElementById('fcHanzi').textContent = card.hanzi;
  document.getElementById('fcPinyin').textContent = card.pinyin;
  document.getElementById('fcMeaning').textContent = card.meaning;
  document.getElementById('fcExampleCn').textContent = card.exCn;
  document.getElementById('fcExamplePy').textContent = card.exPy;
  document.getElementById('fcExampleBm').textContent = card.exBm;

  document.getElementById('btnPrevCard').disabled = idx === 0;
  document.getElementById('btnNextCard').textContent =
    idx < cards.length - 1 ? 'Kad Seterusnya →' : 'Tamat Kad Imbas & Kumpul ⭐';

  if (appState.settings.autoSpeak) {
    speakMandarin(card.hanzi);
  }
}

// --- MOD 2: KUIZ INTERAKTIF (STESEN 2 - 7) ---
function renderQuizQuestion() {
  stopAllLocalSfx();
  const questions = getActiveStationItems(appState.currentStationId);
  const q = questions[appState.currentItemIndex];
  const num = appState.currentItemIndex + 1;

  recordLastActivity(appState.currentLevel, appState.currentChapterId, appState.currentStationId, appState.currentItemIndex);

  document.getElementById('arenaCounter').textContent = `${num}/5`;
  document.getElementById('arenaProgressFill').style.width = `${(num / 5) * 100}%`;
  document.getElementById('challengePill').textContent = `CABARAN ${num} DARIPADA 5`;
  document.getElementById('quizInstruction').textContent = q.instruction;

  const feedbackBox = document.getElementById('feedbackBox');
  const nextBtn = document.getElementById('btnNextChallenge');
  const optionsGrid = document.getElementById('optionsGrid');
  const builderArea = document.getElementById('builderArea');
  const promptEmoji = document.getElementById('promptEmoji');
  const promptHanzi = document.getElementById('promptHanzi');
  const promptPinyin = document.getElementById('promptPinyin');
  const promptPassage = document.getElementById('promptPassage');
  const quizQuestionText = document.getElementById('quizQuestionText');

  feedbackBox.classList.add('hidden');
  feedbackBox.classList.remove('error');
  nextBtn.classList.add('hidden');
  optionsGrid.innerHTML = '';
  optionsGrid.classList.add('hidden');
  builderArea.classList.add('hidden');
  promptEmoji.classList.add('hidden');
  promptPassage.classList.add('hidden');
  quizQuestionText.classList.add('hidden');
  promptHanzi.classList.remove('hidden');
  promptPinyin.classList.remove('hidden');

  if (q.emoji) {
    promptEmoji.textContent = q.emoji;
    promptEmoji.classList.remove('hidden');
  }

  if (q.type === 'mcq') {
    promptHanzi.textContent = q.hanzi;
    promptPinyin.textContent = appState.settings.showPinyin ? q.pinyin : '';
    optionsGrid.classList.remove('hidden');

    q.options.forEach((opt) => {
      const btn = document.createElement('button');
      btn.className = 'option-btn';
      btn.textContent = opt;
      btn.addEventListener('click', () => handleMcqAnswer(btn, opt, q));
      optionsGrid.appendChild(btn);
    });
  } else if (q.type === 'comprehension') {
    promptHanzi.classList.add('hidden');
    promptPinyin.classList.add('hidden');
    promptPassage.classList.remove('hidden');
    promptPassage.innerText = q.passage;

    quizQuestionText.classList.remove('hidden');
    quizQuestionText.textContent = q.question;

    optionsGrid.classList.remove('hidden');
    q.options.forEach((opt) => {
      const btn = document.createElement('button');
      btn.className = 'option-btn';
      btn.textContent = opt;
      btn.addEventListener('click', () => handleMcqAnswer(btn, opt, q));
      optionsGrid.appendChild(btn);
    });
  } else if (q.type === 'order') {
    promptHanzi.textContent = q.promptText;
    promptPinyin.textContent = 'Susun perkataan menjadi ayat yang betul';
    builderArea.classList.remove('hidden');
    appState.selectedOrderWords = [];
    renderOrderBuilder(q);
  }

  document.getElementById('btnSpeakAudio').onclick = () => speakMandarin(q.speech);
  if (appState.settings.autoSpeak) {
    speakMandarin(q.speech);
  }
}

function handleMcqAnswer(clickedBtn, selectedOpt, q) {
  const allBtns = document.querySelectorAll('#optionsGrid .option-btn');
  allBtns.forEach((b) => (b.disabled = true));

  const isCorrect = selectedOpt === q.answer;
  if (isCorrect) {
    clickedBtn.classList.add('correct');
    playKidCheeringSfx(q.speech);
    showQuizFeedback(true, q.explanation);
  } else {
    clickedBtn.classList.add('wrong');
    allBtns.forEach((b) => {
      if (b.textContent === q.answer) b.classList.add('correct');
    });
    playKidWrongSfx();
    showQuizFeedback(false, `Jawapan tepat ialah "${q.answer}". ${q.explanation}`);
  }
}

function renderOrderBuilder(q) {
  const dropzone = document.getElementById('answerDropzone');
  const wordBank = document.getElementById('wordBank');
  dropzone.innerHTML = '';
  wordBank.innerHTML = '';

  if (appState.selectedOrderWords.length === 0) {
    dropzone.innerHTML = '<span class="dropzone-placeholder">Ketik blok perkataan di bawah mengikut urutan</span>';
  } else {
    appState.selectedOrderWords.forEach((word, idx) => {
      const chip = document.createElement('button');
      chip.className = 'word-chip selected';
      chip.textContent = word;
      chip.addEventListener('click', () => {
        appState.selectedOrderWords.splice(idx, 1);
        renderOrderBuilder(q);
      });
      dropzone.appendChild(chip);
    });
  }

  q.words.forEach((word) => {
    const isUsed = appState.selectedOrderWords.includes(word);
    const chip = document.createElement('button');
    chip.className = 'word-chip';
    chip.textContent = word;
    chip.disabled = isUsed;
    chip.style.opacity = isUsed ? '0.35' : '1';
    chip.addEventListener('click', () => {
      appState.selectedOrderWords.push(word);
      renderOrderBuilder(q);
    });
    wordBank.appendChild(chip);
  });

  document.getElementById('btnCheckOrder').onclick = () => {
    if (appState.selectedOrderWords.length === 0) return;
    const isCorrect = appState.selectedOrderWords.join('') === q.answerOrder.join('');
    if (isCorrect) {
      playKidCheeringSfx(q.speech);
      showQuizFeedback(true, q.explanation);
    } else {
      playKidWrongSfx();
      showQuizFeedback(false, `Susunan tepat: ${q.answerOrder.join(' ')}. ${q.explanation}`);
    }
  };
}

function showQuizFeedback(isSuccess, detailText) {
  const feedbackBox = document.getElementById('feedbackBox');
  const feedbackTitle = document.getElementById('feedbackTitle');
  const feedbackDetail = document.getElementById('feedbackDetail');
  const nextBtn = document.getElementById('btnNextChallenge');

  feedbackBox.classList.add('hidden');
  void feedbackBox.offsetWidth;

  feedbackBox.classList.remove('hidden', 'error');
  if (isSuccess) {
    feedbackTitle.textContent = '🎉 Yay! Jawapan Betul!';
  } else {
    feedbackBox.classList.add('error');
    feedbackTitle.textContent = '💡 Kurang tepat! Lihat pembetulan di bawah:';
  }
  feedbackDetail.textContent = detailText;

  nextBtn.classList.remove('hidden');
  nextBtn.textContent =
    appState.currentItemIndex < 4 ? 'Cabaran Seterusnya →' : 'Tamatkan Stesen & Kumpul ⭐';
}

function completeCurrentStation() {
  const lvl = appState.currentLevel;
  const key = `${appState.currentChapterId}_${appState.currentStationId}`;
  if (!appState.progress[lvl].completed[key]) {
    appState.progress[lvl].completed[key] = true;
    appState.progress[lvl].stars += 3;
  }

  const idx = STATIONS_META.findIndex((s) => s.id === appState.currentStationId);
  const nextSt = STATIONS_META[idx + 1] || STATIONS_META[0];
  recordLastActivity(lvl, appState.currentChapterId, nextSt.id, 0);

  playKidCheeringSfx();
  openChapterStations(appState.currentChapterId);
}

// =========================================================
// TAB 2: PRESTASI & LAPORAN PDF
// =========================================================
function renderProgressTab() {
  const lvl = appState.currentLevel;
  const lvlData = SYLLABUS_CHAPTERS[lvl];
  const lvlProgress = appState.progress[lvl];
  const completedCount = Object.keys(lvlProgress.completed).length;

  document.getElementById('reportLevelSubtitle').textContent =
    `MAZ MANDARIN AKADEMIK · ${lvlData.label}`;
  document.getElementById('reportCompletedWorlds').textContent = `${completedCount}/49`;
  document.getElementById('reportTotalStars').textContent = lvlProgress.stars;

  const list = document.getElementById('reportBreakdownList');
  list.innerHTML = '';
  lvlData.chapters.forEach((chap) => {
    let done = 0;
    STATIONS_META.forEach((st) => {
      if (lvlProgress.completed[`${chap.id}_${st.id}`]) done++;
    });
    const row = document.createElement('div');
    row.className = 'report-row';
    row.innerHTML = `
      <span><strong>${chap.num}:</strong> ${chap.title}</span>
      <strong>${done}/7 Stesen</strong>
    `;
    list.appendChild(row);
  });
}

// =========================================================
// INITIALIZE APP SAFELY ON DOM READY
// =========================================================
function initApp() {
  loadState();
  populateMandarinVoices();

  const togglePinyinEl = document.getElementById('togglePinyin');
  const toggleSoundFxEl = document.getElementById('toggleSoundFx');
  const toggleAutoSpeakEl = document.getElementById('toggleAutoSpeak');

  if (togglePinyinEl) togglePinyinEl.checked = appState.settings.showPinyin;
  if (toggleSoundFxEl) toggleSoundFxEl.checked = appState.settings.soundFx;
  if (toggleAutoSpeakEl) toggleAutoSpeakEl.checked = appState.settings.autoSpeak;

  document.querySelectorAll('.dock-item').forEach((btn) => {
    btn.addEventListener('click', () => switchMainTab(btn.dataset.tab));
  });

  document.querySelectorAll('.seg-btn').forEach((btn) => {
    btn.addEventListener('click', () => {
      appState.currentLevel = btn.dataset.level;
      saveState();
      renderChaptersGrid();
    });
  });

  const bindClick = (id, fn) => {
    const el = document.getElementById(id);
    if (el) el.addEventListener('click', fn);
  };

  bindClick('btnHomeBrand', () => switchMainTab('viewAcademy'));
  bindClick('btnBackToChapters', () => {
    renderChaptersGrid();
    showAcademySubView('subViewChapters');
  });
  bindClick('btnBackToStations', () => {
    openChapterStations(appState.currentChapterId);
  });
  bindClick('btnPrintReport', () => window.print());
  bindClick('btnTestVoice', () => speakMandarin('你好！欢迎来到马兹华语学院。'));

  bindClick('flashcardBox', (e) => {
    if (e.target && e.target.id === 'btnFcSpeak') return;
    const inner = document.getElementById('flashcardInner');
    if (inner) inner.classList.toggle('flipped');
  });

  bindClick('btnFcSpeak', (e) => {
    e.stopPropagation();
    const card = getActiveStationItems('s1')[appState.currentItemIndex];
    if (card) speakMandarin(card.hanzi);
  });

  bindClick('btnPrevCard', () => {
    if (appState.currentItemIndex > 0) {
      appState.currentItemIndex--;
      renderFlashcard();
    }
  });

  bindClick('btnNextCard', () => {
    const cards = getActiveStationItems('s1');
    if (appState.currentItemIndex < cards.length - 1) {
      appState.currentItemIndex++;
      renderFlashcard();
    } else {
      completeCurrentStation();
    }
  });

  bindClick('btnNextChallenge', () => {
    stopAllLocalSfx();
    if (appState.currentItemIndex < 4) {
      appState.currentItemIndex++;
      renderQuizQuestion();
    } else {
      completeCurrentStation();
    }
  });

  bindClick('btnResetProgress', () => {
    if (confirm('Adakah anda pasti mahu memadam rekod kemajuan tingkatan ini?')) {
      appState.progress[appState.currentLevel] = { stars: 0, completed: {} };
      appState.lastActivity = null;
      saveState();
      renderChaptersGrid();
      renderProgressTab();
    }
  });

  bindClick('btnCloseLicense', () => {
    const modal = document.getElementById('modalLicense');
    if (modal) modal.classList.add('hidden');
  });

  bindClick('btnActivateLicense', () => {
    const inp = document.getElementById('inputLicenseKey');
    const err = document.getElementById('licenseErrorMsg');
    const code = inp ? inp.value.trim().toUpperCase() : '';
    if (VALID_LICENSE_KEYS.includes(code)) {
      unlockFullAccess();
    } else if (err) {
      err.classList.remove('hidden');
    }
  });

  if (togglePinyinEl) {
    togglePinyinEl.addEventListener('change', (e) => {
      appState.settings.showPinyin = e.target.checked;
      saveState();
    });
  }

  if (toggleSoundFxEl) {
    toggleSoundFxEl.addEventListener('change', (e) => {
      appState.settings.soundFx = e.target.checked;
      saveState();
    });
  }

  if (toggleAutoSpeakEl) {
    toggleAutoSpeakEl.addEventListener('change', (e) => {
      appState.settings.autoSpeak = e.target.checked;
      saveState();
    });
  }

  const selectVoiceEl = document.getElementById('selectMandarinVoice');
  if (selectVoiceEl) {
    selectVoiceEl.addEventListener('change', (e) => {
      appState.settings.preferredVoice = e.target.value;
      saveState();
    });
  }

  renderChaptersGrid();

  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./sw.js').catch(() => {});
  }
}

if (document.readyState === 'loading') {
  document.addEventListener('DOMContentLoaded', initApp);
} else {
  initApp();
}