package com.example.qoutesapp

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qoutesapp.ui.theme.QoutesAppTheme
import com.example.qoutesapp.ui.theme.color1
import com.example.qoutesapp.ui.theme.color10
import com.example.qoutesapp.ui.theme.color2
import com.example.qoutesapp.ui.theme.color3
import com.example.qoutesapp.ui.theme.color4
import com.example.qoutesapp.ui.theme.color5
import com.example.qoutesapp.ui.theme.color6
import com.example.qoutesapp.ui.theme.color7
import com.example.qoutesapp.ui.theme.color8
import com.example.qoutesapp.ui.theme.color9
import kotlin.random.Random

class QuotesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QoutesAppTheme {
               val data = intent.getStringExtra("Data")
                when (data) {
                    "Coding" -> QuoteScreen(list = CodingQuotesList())
                    "Motivation" -> QuoteScreen(list = MotivationQuotesList())
                    "Funny"-> QuoteScreen(list = FunnyQuotesList())
                    "Nature"-> QuoteScreen(list = NatureQuotesList())
                    "Imagination"-> QuoteScreen(list = ImaginationQuotesList())
                    "Relationship"-> QuoteScreen(list = RelationshipQuotesList())
                    "Friendship"-> QuoteScreen(list = FriendshipQuotesList())
                    "Positive"-> QuoteScreen(list = PositiveQuotesList())
                    "Success"-> QuoteScreen(list = SuccessQuotesList())
                    "Health"-> QuoteScreen(list = HealthQuotesList())
                    "Horror"-> QuoteScreen(list = HorrorQuotesList())
                    "Leadership"-> QuoteScreen(list = LeadershipQuotesList())
                    "Islamic"-> QuoteScreen(list = IslamicQuotesList())
                }

            }
    }
}}
@Composable
fun QuoteScreen(list: MutableList<Quotes>) {
    val context = LocalContext.current
    var quoteState by remember{
        mutableStateOf(Random.nextInt(0,list.size))
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize()
            .background(
                Brush.sweepGradient(
                    colors = listOf(
                        Color.White,
                        Color.Black.copy(alpha = .6f)

                    )
                )
            )
        )
    {
        FloatingActionButton(onClick = {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,"${list[quoteState % list.size].title} \n\n ${list[quoteState % list.size].quote}\n\nsaid by ${list[quoteState % list.size].Author}".trimMargin())

            // Optionally, you can specify the package name of the app you want to share with
            // shareIntent.setPackage("com.whatsapp") // Example: Share only with WhatsApp

            context.startActivity(shareIntent)
        }, modifier = Modifier
            .align(Alignment.BottomEnd)
            .animateContentSize()
            .padding(0.dp, 0.dp, 20.dp, 40.dp),
            containerColor = Color.Black,
            contentColor = Color.White) {
            Icon(imageVector = Icons.Filled.Share, contentDescription = null)
        }
        Box(
            Modifier
                .fillMaxWidth()
                .animateContentSize()
                .height(40.dp)
                .background(list[quoteState % list.size].color.copy(alpha = .3f))){

        }

        Button(onClick = {quoteState = Random.nextInt(0,list.size)},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(44.dp)) {
            Text(text = "Next")
        }
        Card(
            modifier = Modifier
                .width(280.dp)
                .animateContentSize(),
            elevation = CardDefaults.cardElevation(
            ),
            colors = CardDefaults.cardColors(
                containerColor = list[quoteState % list.size].color,
                contentColor = Color.White.copy(alpha = .7f)
            )

        ){
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)){
                var state by remember{ mutableStateOf(false) }
                Text(text = list[quoteState % list.size].title,
                    style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = list[quoteState % list.size].quote,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Light,
                    maxLines = if (state) 100 else 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.clickable {
                        state = !state
                    }

                )
                Spacer(modifier = Modifier.height(4.dp))
               Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically){
                   Image(
                       painter = painterResource(id = list[quoteState % list.size].AuthorPic),
                       contentDescription = "",
                       contentScale = ContentScale.Crop,
                       modifier = Modifier
                           .size(48.dp)
                           .clip(CircleShape)
                   )
                   Column(modifier = Modifier.fillMaxWidth(1f),verticalArrangement = Arrangement.spacedBy(2.dp)){
                       Text(text = list[quoteState % list.size].Author, style = MaterialTheme.typography.titleMedium)
                       Text(
                           text = list[quoteState % list.size].AuthorDesignation,
                           style = MaterialTheme.typography.titleMedium,
                           fontWeight = FontWeight.Light
                       )
                   }

               }
            }
        }
    }
}

data class Quotes(
    val title: String,
    val quote: String,
    val Author:String,
    val AuthorDesignation:String,
    val color:Color,
    @DrawableRes val AuthorPic: Int
)

fun MotivationQuotesList(): MutableList<Quotes> {
    val QuotesList = mutableListOf<Quotes>()
    QuotesList.add(
        Quotes(
            title = "Courage",
            quote= "Courage is not just a virtue; it's the strength to face fears head-on. It's the audacity to step into the arena of uncertainty and emerge victorious. Cultivate courage, for it's the force that transforms dreams into reality.",
            Author = "Olivia Turner",
            AuthorDesignation = "Courage Coach",
            color = color1,
            AuthorPic = R.drawable.c
    )
    )
    QuotesList.add(
        Quotes(
            title = "Purpose",
            quote= "Discovering your purpose is not a destination but a journey. It's the continuous exploration of your passions and values. A life with purpose is a life well-lived, a journey where each step aligns with the heartbeat of your soul.",
            Author = "Jonathan Brooks",
            AuthorDesignation = "Life Coach",
            color = color2,
            AuthorPic = R.drawable.c
        )
    )
    QuotesList.add(
        Quotes(
            title = "Love",
            quote= "Love is not just an emotion; it's the fabric that weaves the tapestry of human connection. It's the willingness to understand, the power to heal. Embrace love, for it's the force that binds us in the grand symphony of existence.",
            Author = "Elena Rodriguez",
            AuthorDesignation = "Love Advocate",
            color = color3,
            AuthorPic = R.drawable.g
        )
    )
    QuotesList.add(
        Quotes(
            title = "Innovation",
            quote= "Innovation is not just about inventions; it's a mindset that sparks transformation. It's the courage to challenge the status quo and pave new paths. Be an innovator, for the future belongs to those who dare to reimagine it.",
            Author = "Sophia Thompson",
            AuthorDesignation = "Innovation Enthusiast",
            color = color4,
            AuthorPic = R.drawable.i
        )
    )
    QuotesList.add(
        Quotes(
            title = "Success",
            quote= "Success is not final, failure is not fatal: It is the courage to continue that counts.",
            Author = "Winston Churchill",
            AuthorDesignation = "Former Prime Minister of the UK",
            color = color5,
            AuthorPic = R.drawable.b
        )
    )
    QuotesList.add(
        Quotes(
            title = "Imagination",
            quote= "Imagination is more important than knowledge.",
            Author = "Albert Einstein",
            AuthorDesignation = "Theoretical Physicist",
            color = color6,
            AuthorPic = R.drawable.d
        )
    )
    QuotesList.add(
        Quotes(
            title = "Time",
            quote= "Time is not a ticking clock but a canvas waiting for your brushstroke. Each moment is a stroke of opportunity, a chance to paint the masterpiece of your life. Don't watch time pass; instead, create the art that time will remember.",
            Author = "Isabella Turner",
            AuthorDesignation = "Artist",
            color = color7,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Adventure",
            quote= "Life is the greatest adventure, and every moment is a step into the unknown. Embrace the uncertainties, for it is in the journey that we discover our true selves. Adventure awaits those who dare to leave the comfort of the shore.",
            Author = "Alexandra Williams",
            AuthorDesignation = "Explorer",
            color = color8,
            AuthorPic = R.drawable.h
        )
    )
    QuotesList.add(
        Quotes(
            title = "Inspiration",
            quote= "Inspiration is not a mere spark; it's a raging fire that fuels our dreams. It's the invisible force that propels us forward when the path seems dark. Embrace inspiration, for in its light, we discover the extraordinary within the ordinary.",
            Author = "Sophia Johnson",
            AuthorDesignation = "Motivational Speaker",
            color = color1,
            AuthorPic = R.drawable.l
        )
    )

    QuotesList.add(
        Quotes(
            title = "Connection",
            quote= "In the tapestry of life, threads of connection weave a story of shared experiences. It's in the bonds we form that the fabric becomes rich and vibrant. Cherish connections, for they are the knots that hold the tapestry together.",
            Author = "Daniel Rodriguez",
            AuthorDesignation = "Social Psychologist",
            color = color2,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Gratitude",
            quote= "Gratitude is not just a feeling; it's a way of life. In counting our blessings, we discover the abundance that surrounds us. Gratitude turns what we have into enough, and it's the key that unlocks the fullness of every moment.",
            Author = "Maria Sanchez",
            AuthorDesignation = "Gratitude Coach",
            color = color3,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Resilience",
            quote= "Life is not about waiting for the storm to pass, but about learning how to dance in the rain. In the face of adversity, resilience is the key. It's not the absence of challenges that defines us, but our ability to rise after falling.",
            Author = "Unknown",
            AuthorDesignation = "Philosopher",
            color = color4,
            AuthorPic = R.drawable.i
        )
    )
    QuotesList.add(
        Quotes(
            title = "Change",
            quote= "Change is the only constant, a river that flows through the landscape of our lives. Embrace change, for it is not the enemy but the sculptor of new possibilities. In the dance of transformation, find the rhythm of your evolving self.",
            Author = "Elijah Thompson",
            AuthorDesignation = "Change Catalyst",
            color = color5,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
        Quotes(
            title = "Optimism",
            quote= "Optimism is not just a viewpoint; it's a force that propels us toward a brighter tomorrow. It's the belief that every cloud has a silver lining and every setback is a setup for a comeback. Choose optimism, for it is the sun that breaks through the darkest clouds.",
            Author = "Olivia Carter",
            AuthorDesignation = "Positive Psychologist",
            color = color6,
            AuthorPic = R.drawable.d
        )
    )
    QuotesList.add(
        Quotes(
            title = "Legacy",
            quote= "A legacy is not just what we leave behind; it's the impact we make in the lives of others. It's the echoes of our kindness, the ripple effect of our actions. Live with purpose, for a life well-lived is a legacy that transcends time.",
            Author = "Samuel Wright",
            AuthorDesignation = "Philanthropist",
            color = color7,
            AuthorPic = R.drawable.c
        )
    )
    QuotesList.add(
        Quotes(
            title = "Mindfulness",
            quote= "Mindfulness is not just a practice; it's a state of being fully present. It's the art of savoring each moment, finding joy in the simple things. In the stillness of the present, discover the vastness of your inner universe.",
            Author = "Mia Foster",
            AuthorDesignation = "Mindfulness Teacher",
            color = color8,
            AuthorPic = R.drawable.b
        )
    )
    QuotesList.add(
        Quotes(
            title = "Innovation",
            quote= "Innovation is not just about new ideas; it's about daring to challenge the status quo. It's the willingness to step into the unknown, fueled by a curiosity that knows no bounds. Be an innovator, for the future belongs to the bold.",
            Author = "Nathan Anderson",
            AuthorDesignation = "Innovation Evangelist",
            color =  color9,
            AuthorPic = R.drawable.e
        )
    )
    QuotesList.add(
        Quotes(
            title = "Adventure",
            quote= "Adventure is not just a journey; it's a mindset that turns the mundane into the extraordinary. It's the spirit of exploration that fuels the flame of curiosity. Embrace the adventure, for it's in the unknown that we discover our true capabilities.",
            Author = "Liam Turner",
            AuthorDesignation = "Explorer",
            color = color10,
            AuthorPic = R.drawable.h
        )
    )
    QuotesList.add(
        Quotes(
            title = "Faith",
            quote= "Faith is not just a belief; it's a journey of trust in the unseen. It's the anchor that keeps us steady in the storm, the wings that lift us when we dare to dream. Nurture faith, for it's the bridge between what is and what can be.",
            Author = "Sophie Adams",
            AuthorDesignation = "Spiritual Guide",
            color = color1,
            AuthorPic = R.drawable.j
        )
    )
    QuotesList.add(
        Quotes(
            title = "Kindness",
            quote= "Kindness is not just a gesture; it's a language that transcends barriers. It's the warmth in a smile, the gentleness in a touch. Practice kindness, for it's the melody that harmonizes the symphony of humanity.",
            Author = "Michael Hayes",
            AuthorDesignation = "Kindness Advocate",
            color = color2,
            AuthorPic = R.drawable.g
        )
    )
    QuotesList.add(
        Quotes(
            title = "Kindness",
            quote= "Kindness is not just a gesture; it's a language that transcends barriers. It's the warmth in a smile, the gentleness in a touch. Practice kindness, for it's the melody that harmonizes the symphony of humanity.",
            Author = "Michael Hayes",
            AuthorDesignation = "Kindness Advocate",
            color = color3,
            AuthorPic = R.drawable.g
        )
    )
    QuotesList.add(
        Quotes(
            title = "Purpose",
            quote= "Purpose is not just a goal; it's the heartbeat of a meaningful existence. It's the alignment of passion with contribution. Discover your purpose, for it's the compass that guides you through the vast landscape of life.",
            Author = "Emma Thompson",
            AuthorDesignation = "Purpose Coach",
            color = color4,
            AuthorPic = R.drawable.j
        )
    )
    QuotesList.add(
        Quotes(
            title = "Success",
            quote= "Success is not just an achievement; it's the journey of growth and resilience. It's the realization that setbacks are stepping stones, and every failure is a lesson. Pursue success, for it's the symphony of hard work and perseverance.",
            Author = "Brandon Carter",
            AuthorDesignation = "Success Mentor",
            color = color5,
            AuthorPic = R.drawable.c
        )
    )
    QuotesList.add(
        Quotes(
            title = "Leadership",
            quote= "Leadership is not just a position; it's the ability to inspire and empower. It's the recognition that true leaders create more leaders. Lead with integrity, for it's the foundation upon which great legacies are built.",
            Author = "Sophia Martinez",
            AuthorDesignation = "Leadership Consultant",
            color = color5,
            AuthorPic = R.drawable.d
        )
    )
    QuotesList.add(
        Quotes(
            title = "Balance",
            quote= "Balance is not just a state; it's the art of juggling priorities. It's the harmony between work and play, ambition and contentment. Seek balance, for it's the key that unlocks the door to a fulfilled and harmonious life.",
            Author = "Ethan Turner",
            AuthorDesignation = "Life Balance Coach",
            color = color6,
            AuthorPic = R.drawable.l
        )
    )
    QuotesList.add(
        Quotes(
            title = "Perseverance",
            quote= "Perseverance is not just endurance; it's the courage to push forward despite adversity. It's the refusal to be defined by challenges but rather shaped by them. Embrace perseverance, for it's the bridge between dreams and reality.",
            Author = "Isaac Roberts",
            AuthorDesignation = "Resilience Expert",
            color = color7,
            AuthorPic = R.drawable.d
        )
    )
    QuotesList.add(
        Quotes(
            title = "Mindfulness",
            quote= "Mindfulness is not just a practice; it's a way of being fully alive. It's the art of living in the present, savoring each moment without judgment. In the stillness of mindfulness, discover the profound beauty of existence.",
            Author = "Mia Turner",
            AuthorDesignation = "Mindfulness Teacher",
            color = color8,
            AuthorPic = R.drawable.c
        )
    )
    QuotesList.add(
        Quotes(
            title = "Gratitude",
            quote= "Gratitude is not just a feeling; it's a transformative force. It's the recognition that even in the ordinary, there is extraordinary beauty. Practice gratitude, for it's the key that unlocks the fullness of each moment.",
            Author = "Elijah Turner",
            AuthorDesignation = "Gratitude Evangelist",
            color = color9,
            AuthorPic = R.drawable.i
        )
    )
    QuotesList.add(
        Quotes(
            title = "Legacy",
            quote= "Legacy is not just what we leave behind; it's the impact we imprint on the world. It's the echo of our values resonating through the lives we touch. Live with purpose, for a purposeful life is a legacy that transcends time.",
            Author = "Sophia Hayes",
            AuthorDesignation = "Legacy Builder",
            color = color10,
            AuthorPic = R.drawable.g
        )
    )


  return QuotesList
}
fun CodingQuotesList(): MutableList<Quotes> {
    val QuotesList = mutableListOf<Quotes>()


    QuotesList.add(
        Quotes(
            title = "Debugging",
            quote = "Debugging is like being the detective in a crime movie where you are also the murderer.",
            Author = "Unknown",
            AuthorDesignation = "Code Detective",
            color = color1,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Syntax Error",
            quote = "Why do programmers prefer dark mode? Because light attracts bugs!",
            Author = "Anonymous",
            AuthorDesignation = "Light Avoider",
            color = color2,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Coffee & Code",
            quote = "I don't need an inspirational quote; I need coffee and code!",
            Author = "Caffeine Coder",
            AuthorDesignation = "Code Brewer",
            color = color3,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Programming Logic",
            quote = "Programming logic: If coffee, then code; else, error!",
            Author = "Logic Lover",
            AuthorDesignation = "Code Philosopher",
            color = color4,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Commitment Issues",
            quote = "I have a lot of commitment issues. Specifically, with Git!",
            Author = "Git Jester",
            AuthorDesignation = "Version Control Comedian",
            color = color5,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Programmers' Excuse",
            quote = "The code works on my machine; the universe is obviously conspiring against me!",
            Author = "Excuse Inventor",
            AuthorDesignation = "Code Apologist",
            color = color6,
            AuthorPic = R.drawable.l
        )
    )

    QuotesList.add(
        Quotes(
            title = "Infinite Loop",
            quote = "Why do programmers always mix up Christmas and Halloween? Because Oct 31 == Dec 25!",
            Author = "Joke Compiler",
            AuthorDesignation = "Code Humorist",
            color = color7,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Coffee-driven Development",
            quote = "I'm not lazy; I'm in energy-saving mode.",
            Author = "Caffeine Enthusiast",
            AuthorDesignation = "Energy-Saver Coder",
            color = color8,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Code Poetry",
            quote = "Roses are red, Violets are blue, Unexpected '{' on line 32.",
            Author = "Poetic Programmer",
            AuthorDesignation = "Code Bard",
            color = color9,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Software Updates",
            quote = "Why do programmers always mix up Christmas and Halloween? Because Oct 31 == Dec 25!",
            Author = "Joke Compiler",
            AuthorDesignation = "Code Humorist",
            color = color10,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
            Quotes(
                title = "Coding Mastery",
                quote = "Code like a wizard, think like an algorithm.\nIn the symphony of syntax, every line plays a crucial note.\nDebugging is the art of unraveling the intricate threads of logic.",
                Author = "CodeMaster",
                AuthorDesignation = "Software Engineer",
                color = color1,
                AuthorPic = R.drawable.j
            )
        )

        QuotesList.add(
            Quotes(
                title = "Algorithmic Wonders",
                quote = "In the realm of coding, every bug is a puzzle waiting to be solved.\nAlgorithms are the poetry of logical thinking.\nCode is the brush; the program, a masterpiece on the canvas of computation.",
                Author = "DevGenius",
                AuthorDesignation = "Software Engineer",
                color = color2,
                AuthorPic = R.drawable.b
            )
        )

        QuotesList.add(
            Quotes(
                title = "Debugging Chronicles",
                quote = "Programming is the art of turning ideas into instructions for machines.\nIn the labyrinth of code, a debugger is the guiding light.\nTo code is to create, and debugging is the refining fire of creation.",
                Author = "TechMaestro",
                AuthorDesignation = "Software Engineer",
                color = color3,
                AuthorPic = R.drawable.c
            )
        )
    QuotesList.add(
        Quotes(
            title = "Code Optimization",
            quote = "In the world of coding, optimization is the art of achieving elegance through efficiency.\nCode is like a garden; regular pruning ensures a thriving ecosystem.\nOptimizing is not just about speed; it's about crafting code that dances with grace.",
            Author = "EfficiencyEnthusiast",
            AuthorDesignation = "Software Architect",
            color = color4,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Programming Philosophy",
            quote = "Programming is not just about solving problems; it's about creating solutions that stand the test of time.\nThe language of code speaks the eloquence of logic.\nIn the algorithmic symphony, each function is a note in harmony.",
            Author = "LogicLinguist",
            AuthorDesignation = "Coding Philosopher",
            color = color5,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Infinite Loop of Learning",
            quote = "The loop of learning in coding has no exit condition; it's a journey without a destination.\nIn the infinite library of programming, each book is a lesson waiting to be explored.\nEmbrace the challenge, for it is the forge of expertise.",
            Author = "EternalLearner",
            AuthorDesignation = "Coding Scholar",
            color = color6,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Code Artistry",
            quote = "Code is not merely a set of instructions; it's the canvas where logic transforms into art.\nIndentation is the brushstroke, and syntax is the color palette in the masterpiece of programming.\nIn the labyrinth of code, each line tells a story waiting to be deciphered.",
            Author = "CodeArtisan",
            AuthorDesignation = "Logic Painter",
            color = color1,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Blockchain Philosophy",
            quote = "In the chain of blocks, trust is the cornerstone; transparency is the mortar.\nDecentralization is not just a concept; it's the liberation of data from the shackles of central authority.\nThe genesis block is the birthplace of possibilities, where the journey begins.",
            Author = "CryptoPhilosopher",
            AuthorDesignation = "Decentralization Sage",
            color = color2,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "User Interface Elegance",
            quote = "In the realm of UI, simplicity is the crown jewel, and user experience is the reigning monarch.\nEach pixel is a storyteller, and every click is a plot twist in the narrative of user interaction.\nDesign is not just about visuals; it's about crafting an immersive journey.",
            Author = "UIDesignMaestro",
            AuthorDesignation = "Experience Architect",
            color = color3,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "DevOps Symphony",
            quote = "In the orchestra of DevOps, collaboration is the melody, and automation is the rhythm.\nContinuous integration is the harmonious blend of code contributions, a symphony of seamless collaboration.\nDeployment is the grand finale, where the curtain rises on the stage of production.",
            Author = "DevOpsComposer",
            AuthorDesignation = "Collaboration Conductor",
            color = color4,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Cybersecurity Wisdom",
            quote = "In the digital fortress, encryption is the guardian, and vulnerability assessments are the gatekeepers.\nSecurity is not just a feature; it's the shield that safeguards the integrity of data.\nA strong password is not just a combination of characters; it's a mantra of protection.",
            Author = "SecureGuardian",
            AuthorDesignation = "Cyber Sentinel",
            color = color5,
            AuthorPic = R.drawable.e
        )
    )

    // Add more items with customized values for title, quote, and author as needed
    QuotesList.add(
        Quotes(
            title = "Bug Hunt",
            quote = "In the kingdom of code, bugs are the elusive creatures hiding in the shadows of logic.\nDebugging is not just fixing errors; it's the art of becoming a code detective, unraveling the mystery of unexpected behavior.\nThe bug hunt is a quest for perfection, a journey where every squashed bug is a triumph of logic over chaos.",
            Author = "CodeSleuth",
            AuthorDesignation = "Bug Hunter",
            color = color6,
            AuthorPic = R.drawable.k
        )
    )

    QuotesList.add(
        Quotes(
            title = "Version Control Symphony",
            quote = "Version control is the symphony of collaboration, where branches dance and merges create harmonious compositions.\nCommits are the musical notes, and repositories are the concert halls where the history of code unfolds.\nIn the version control symphony, conflicts are resolved with the grace of a conductor orchestrating a seamless performance.",
            Author = "VCSMaestro",
            AuthorDesignation = "Collaborative Composer",
            color = color7,
            AuthorPic = R.drawable.l
        )
    )

    QuotesList.add(
        Quotes(
            title = "Algorithmic Ballet",
            quote = "Algorithms are not just lines of code; they are the choreography of digital ballets, gracefully executed on the stage of computation.\nEfficiency is the pirouette, and complexity is the dance partner in the algorithmic ballet.\nIn the elegance of algorithms, every step is a leap toward optimization.",
            Author = "AlgorithmDancer",
            AuthorDesignation = "Optimization Choreographer",
            color = color8,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Code Poetry",
            quote = "Code is not just a set of instructions; it's the poetry written in the syntax of logic.\nFunctions are stanzas, and variables are the poetic musings that give life to the narrative.\nIn the realm of code poetry, every line is a verse, and every project is an epic tale.",
            Author = "CodeBard",
            AuthorDesignation = "Poet of Logic",
            color = color9,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Refactoring Rhapsody",
            quote = "Refactoring is the rhapsody of improvement, where code evolves into a symphony of clarity and efficiency.\nCode smells are the dissonant notes, and refactoring is the maestro's touch transforming chaos into harmony.\nIn the refactoring rhapsody, each iteration is a crescendo, elevating the code to new heights.",
            Author = "RefactorMaestro",
            AuthorDesignation = "Harmony Sculptor",
            color = color10,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "AI Enlightenment",
            quote = "In the realm of artificial intelligence, algorithms are the sages, and data is the ancient scrolls.\nMachine learning is the journey into the unknown, a quest for knowledge written in the language of patterns.\nEnlightenment is not in the lines of code but in the insights decoded from the binary symphony.",
            Author = "AIScholar",
            AuthorDesignation = "Algorithmic Sage",
            color = color6,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Quantum Quest",
            quote = "Quantum computing is not just a leap; it's a dance with qubits in the vast arena of superposition.\nEntanglement is the celestial waltz, where particles synchronize their steps across the cosmic stage.\nIn the quantum quest, computation transcends classical boundaries, exploring the uncharted landscapes of probability.",
            Author = "QuantumExplorer",
            AuthorDesignation = "Quantum Pioneer",
            color = color7,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Flutter Symphony",
            quote = "Flutter is not just a framework; it's the conductor orchestrating a symphony of widgets.\nWidgets are the musical notes, and the screen is the canvas where the user interface becomes a visual masterpiece.\nDart is not just a language; it's the lyrical poetry that brings Flutter's composition to life.",
            Author = "FlutterMaestro",
            AuthorDesignation = "UI Composer",
            color = color8,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
        Quotes(
            title = "Cloud Harmony",
            quote = "In the cloud symphony, scalability is the rhythm, and elasticity is the harmony.\nContainers are the virtuoso performers, seamlessly orchestrating the workload.\nCloud architecture is the grand amphitheater where the digital opera unfolds.",
            Author = "CloudComposer",
            AuthorDesignation = "Scalability Virtuoso",
            color = color9,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Pythonic Wisdom",
            quote = "Python is not just a language; it's the serenade of simplicity, sung in the chorus of readability.\nIndentation is the poetic rhythm, and the Zen of Python is the guiding philosophy.\nIn the pythonic realm, elegance is not just a feature; it's the essence of the code.",
            Author = "PythonSorcerer",
            AuthorDesignation = "Code Enchanter",
            color = color10,
            AuthorPic = R.drawable.j
        )
    )


    QuotesList.add(
            Quotes(
                title = "Bug Hunt",
                quote = "In the kingdom of code, bugs are the elusive creatures hiding in the shadows of logic.\nDebugging is not just fixing errors; it's the art of becoming a code detective, unraveling the mystery of unexpected behavior.\nThe bug hunt is a quest for perfection, a journey where every squashed bug is a triumph of logic over chaos.",
                Author = "CodeSleuth",
                AuthorDesignation = "Bug Hunter",
                color = color1,
                AuthorPic = R.drawable.k
            )
        )

    QuotesList.add(
            Quotes(
                title = "Version Control Symphony",
                quote = "Version control is the symphony of collaboration, where branches dance and merges create harmonious compositions.\nCommits are the musical notes, and repositories are the concert halls where the history of code unfolds.\nIn the version control symphony, conflicts are resolved with the grace of a conductor orchestrating a seamless performance.",
                Author = "VCSMaestro",
                AuthorDesignation = "Collaborative Composer",
                color = color2,
                AuthorPic = R.drawable.l
            )
        )

    QuotesList.add(
            Quotes(
                title = "Algorithmic Ballet",
                quote = "Algorithms are not just lines of code; they are the choreography of digital ballets, gracefully executed on the stage of computation.\nEfficiency is the pirouette, and complexity is the dance partner in the algorithmic ballet.\nIn the elegance of algorithms, every step is a leap toward optimization.",
                Author = "AlgorithmDancer",
                AuthorDesignation = "Optimization Choreographer",
                color = color3,
                AuthorPic = R.drawable.g
            )
        )

    QuotesList.add(
            Quotes(
                title = "Code Poetry",
                quote = "Code is not just a set of instructions; it's the poetry written in the syntax of logic.\nFunctions are stanzas, and variables are the poetic musings that give life to the narrative.\nIn the realm of code poetry, every line is a verse, and every project is an epic tale.",
                Author = "CodeBard",
                AuthorDesignation = "Poet of Logic",
                color = color4,
                AuthorPic = R.drawable.c
            )
        )

    QuotesList.add(
            Quotes(
                title = "Refactoring Rhapsody",
                quote = "Refactoring is the rhapsody of improvement, where code evolves into a symphony of clarity and efficiency.\nCode smells are the dissonant notes, and refactoring is the maestro's touch transforming chaos into harmony.\nIn the refactoring rhapsody, each iteration is a crescendo, elevating the code to new heights.",
                Author = "RefactorMaestro",
                AuthorDesignation = "Harmony Sculptor",
                color = color5,
                AuthorPic = R.drawable.e
            )
        )

    QuotesList.add(
            Quotes(
                title = "AI Enlightenment",
                quote = "In the realm of artificial intelligence, algorithms are the sages, and data is the ancient scrolls.\nMachine learning is the journey into the unknown, a quest for knowledge written in the language of patterns.\nEnlightenment is not in the lines of code but in the insights decoded from the binary symphony.",
                Author = "AIScholar",
                AuthorDesignation = "Algorithmic Sage",
                color = color6,
                AuthorPic = R.drawable.c
            )
        )

    QuotesList.add(
            Quotes(
                title = "Quantum Quest",
                quote = "Quantum computing is not just a leap; it's a dance with qubits in the vast arena of superposition.\nEntanglement is the celestial waltz, where particles synchronize their steps across the cosmic stage.\nIn the quantum quest, computation transcends classical boundaries, exploring the uncharted landscapes of probability.",
                Author = "QuantumExplorer",
                AuthorDesignation = "Quantum Pioneer",
                color = color7,
                AuthorPic = R.drawable.g
            )
        )

    QuotesList.add(
            Quotes(
                title = "Flutter Symphony",
                quote = "Flutter is not just a framework; it's the conductor orchestrating a symphony of widgets.\nWidgets are the musical notes, and the screen is the canvas where the user interface becomes a visual masterpiece.\nDart is not just a language; it's the lyrical poetry that brings Flutter's composition to life.",
                Author = "FlutterMaestro",
                AuthorDesignation = "UI Composer",
                color = color8,
                AuthorPic = R.drawable.h
            )
        )

    QuotesList.add(
            Quotes(
                title = "Cloud Harmony",
                quote = "In the cloud symphony, scalability is the rhythm, and elasticity is the harmony.\nContainers are the virtuoso performers, seamlessly orchestrating the workload.\nCloud architecture is the grand amphitheater where the digital opera unfolds.",
                Author = "CloudComposer",
                AuthorDesignation = "Scalability Virtuoso",
                color = color9,
                AuthorPic = R.drawable.i
            )
        )

    QuotesList.add(
            Quotes(
                title = "Pythonic Wisdom",
                quote = "Python is not just a language; it's the serenade of simplicity, sung in the chorus of readability.\nIndentation is the poetic rhythm, and the Zen of Python is the guiding philosophy.\nIn the pythonic realm, elegance is not just a feature; it's the essence of the code.",
                Author = "PythonSorcerer",
                AuthorDesignation = "Code Enchanter",
                color = color10,
                AuthorPic = R.drawable.j
            )
        )



        return QuotesList
    }
fun FunnyQuotesList(): MutableList<Quotes> {
    val QuotesList = mutableListOf<Quotes>()
    QuotesList.add(
        Quotes(
            title = "Life's GPS",
            quote = "Life is like a GPS. Sometimes it tells you to make a U-turn when possible, and other times, it recalculates without warning.",
            Author = "Navigational Philosopher",
            AuthorDesignation = "Life Explorer",
            color = color1,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Adulting Reality",
            quote = "Adulting is like folding a fitted sheet. No one really knows how, and it usually ends up in a crumpled mess.",
            Author = "Sheet Folding Expert",
            AuthorDesignation = "Life Organizer",
            color = color2,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Diet Dilemma",
            quote = "My diet plan: eat whatever I want, and if someone asks, say it's intermittent fasting.",
            Author = "Diet Rebel",
            AuthorDesignation = "Food Enthusiast",
            color = color3,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Sleep Struggle",
            quote = "I'm not a morning person or a night owl. I'm a permanently exhausted pigeon.",
            Author = "Sleep Connoisseur",
            AuthorDesignation = "Dream Chaser",
            color = color4,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Procrastination Wisdom",
            quote = "Procrastination is like a credit card. It's a lot of fun until you get the bill.",
            Author = "Master Procrastinator",
            AuthorDesignation = "Delay Expert",
            color = color5,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Social Media Reality",
            quote = "I don't have a social life. I just have a Wi-Fi connection.",
            Author = "Online Socialite",
            AuthorDesignation = "Digital Nomad",
            color = color6,
            AuthorPic = R.drawable.l
        )
    )

    QuotesList.add(
        Quotes(
            title = "Shopping Wisdom",
            quote = "The easiest way to find something lost around the house is to buy a replacement.",
            Author = "Shopping Guru",
            AuthorDesignation = "Retail Therapist",
            color = color7,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Weather Forecast",
            quote = "My weather app is basically a slot machine. I have no idea what's going on, but I'm hoping for sunshine.",
            Author = "Weather Gambler",
            AuthorDesignation = "Meteorological Optimist",
            color = color8,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Parenting Reality",
            quote = "Parenthood is realizing that your kids are less interested in the toys you bought than the box they came in.",
            Author = "Toy Box Analyst",
            AuthorDesignation = "Parenting Expert",
            color10,
            R.drawable.j))


    QuotesList.add(
        Quotes(
            title = "Technology Dilemma",
            quote = "Technology is a double-edged sword. It connects us and disconnects us at the same time. Maybe that's why it's called the World Wide Web – we're all caught in its tangled threads.",
            Author = "Digital Philosopher",
            AuthorDesignation = "Tech Explorer",
            color = color1,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Fitness Wisdom",
            quote = "My exercise routine consists of running late and jumping to conclusions.",
            Author = "Casual Athlete",
            AuthorDesignation = "Fitness Enthusiast",
            color = color2,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Time Travel",
            quote = "If time travel were possible, I'd go back just to warn myself not to make the decisions that led to me wanting a time machine.",
            Author = "Temporal Strategist",
            AuthorDesignation = "Time Traveler-at-Heart",
            color = color3,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Email Realities",
            quote = "Emails are like zombies. No matter how many you delete, more keep coming back to life.",
            Author = "Inbox Survivor",
            AuthorDesignation = "Email Warrior",
            color = color4,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Weekend Forecast",
            quote = "My weekend forecast: 99% Netflix, 1% pretending to be productive.",
            Author = "Weekend Strategist",
            AuthorDesignation = "Leisure Planner",
            color = color5,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Online Shopping Reality",
            quote = "Online shopping is my cardio. I lift my credit card, and my fingers get a good workout.",
            Author = "Shopping Athlete",
            AuthorDesignation = "E-commerce Enthusiast",
            color = color6,
            AuthorPic = R.drawable.l
        )
    )

    QuotesList.add(
        Quotes(
            title = "Selfie Reflection",
            quote = "A selfie is just a way to prove that your hair looked good and you went outside. The lighting was just a bonus.",
            Author = "Selfie Historian",
            AuthorDesignation = "Photography Enthusiast",
            color = color7,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Pet Philosophy",
            quote = "Owning a pet is like having a therapist who doesn't judge and always thinks you're fascinating. Also, they're excellent listeners.",
            Author = "Pet Psychologist",
            AuthorDesignation = "Animal Whisperer",
            color = color8,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Travel Reality",
            quote = "Traveling is the only expense that makes you richer – until you check your bank account.",
            Author = "Budget Explorer",
            AuthorDesignation = "Wanderlust Planner",
            color = color9,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Weather Wisdom",
            quote = "The weather is like my mood – constantly changing, and I can't control it, no matter how hard I try.",
            Author = "Meteorological Philosopher",
            AuthorDesignation = "Emotional Forecaster",
            color = color10,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
        Quotes(
            title = "DIY Reality",
            quote = "DIY projects are just a series of unfortunate events with glue and paint. But hey, at least it's uniquely mine.",
            Author = "Crafting Adventurer",
            AuthorDesignation = "DIY Enthusiast",
            color = color1,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Coffee Wisdom",
            quote = "Coffee is my love language. It says, 'I love you a latte' without actually saying anything.",
            Author = "Caffeine Enthusiast",
            AuthorDesignation = "Barista Apprentice",
            color = color2,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Monday Motivation",
            quote = "Monday motivation is a myth. My motivation usually shows up on Wednesday, just in time for the weekend countdown.",
            Author = "Weekday Survivor",
            AuthorDesignation = "Motivational Theorist",
            color = color3,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Fitness Dilemma",
            quote = "My favorite exercise is a cross between a lunge and a crunch. It's called lunch.",
            Author = "Gourmet Gymnast",
            AuthorDesignation = "Foodie Fitness Guru",
            color = color1,
            R.drawable.b))

    return QuotesList
}
fun RelationshipQuotesList(): MutableList<Quotes> {
   val QuotesList = mutableListOf<Quotes>()
    QuotesList.add(
        Quotes(
            title = "Harmony of Hearts",
            quote = "Love is the sweet melody that plays in the harmony of our hearts.\nEvery challenge we face is an opportunity to strengthen our bond.\nIn the dance of love, every misstep is just a chance to find a new rhythm together.",
            Author = "MelodyMaker",
            AuthorDesignation = "Love Composer",
            color = color9,
            AuthorPic = R.drawable.j
        )
    )

    QuotesList.add(
        Quotes(
            title = "Puzzle of Love",
            quote = "Our love is like a puzzle, with each piece fitting perfectly into place.\nIn the mosaic of life, love is the most beautiful and vibrant color.\nCherish every moment, for it's the puzzle of love that makes life complete.",
            Author = "PuzzleMaster",
            AuthorDesignation = "Love Artisan",
            color = color1,
            AuthorPic = R.drawable.k
        )
    )

    QuotesList.add(
        Quotes(
            title = "Infinite Affection",
            quote = "True love is an infinite loop of affection that never diminishes.\nIn the vast universe of emotions, our love is a shining star.\nThe more we give, the more our love multiplies, creating an endless bond.",
            Author = "AffectionateSoul",
            AuthorDesignation = "Love Astronomer",
            color = color2,
            AuthorPic = R.drawable.l
        )
    )

    QuotesList.add(
        Quotes(
            title = "Couples' Canvas",
            quote = "Our relationship is a canvas, and every experience is a stroke of love.\nIn the portrait of us, every scar tells a story of resilience and growth.\nTogether, we create a masterpiece that is uniquely ours.",
            Author = "CanvasCreators",
            AuthorDesignation = "Love Artist",
            color = color3,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Endless Devotion",
            quote = "Devotion is the compass that guides us through the journey of love.\nIn the book of love, every chapter is written with unwavering commitment.\nTrue happiness is found in the pages of a love story that never ends.",
            Author = "DevotionSeeker",
            AuthorDesignation = "Love Navigator",
            color = color4,
            AuthorPic = R.drawable.j

        )
    )

    QuotesList.add(
        Quotes(
            title = "Shared Dreams",
            quote = "Our love is a garden where dreams are planted and nurtured.\nIn the soil of commitment, our shared aspirations blossom into reality.\nTogether, we cultivate a future that is as beautiful as our love.",
            Author = "DreamWeavers",
            AuthorDesignation = "Love Gardener",
            color = color4,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Companionship Chronicle",
            quote = "Every day with you is a new page in the chronicle of companionship.\nIn the novel of us, each chapter is a testament to our shared laughter and tears.\nWriting our story together is the most beautiful adventure.",
            Author = "ChronicleCrafters",
            AuthorDesignation = "Love Scribe",
            color = color5,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Parallel Hearts",
            quote = "Our hearts beat in parallel, creating a rhythm that echoes our deep connection.\nIn the parallel universe of love, every decision is made together.\nTwo souls intertwined, creating a love story that transcends time.",
            Author = "ParallelHarmony",
            AuthorDesignation = "Love Conductor",
            color = color6,
            AuthorPic = R.drawable.d
        )
    )
    QuotesList.add(
        Quotes(
            title = "Whispers of the Heart",
            quote = "Love speaks in whispers, heard by the heart rather than the ears.\nIn the silent moments, our connection deepens, echoing the language of emotions.\nA simple touch can convey more than a thousand words ever could.",
            Author = "HeartWhisperer",
            AuthorDesignation = "Emotional Linguist",
            color = color7,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Ephemeral Bliss",
            quote = "Love is the art of finding joy in the transient moments of life.\nIn the fleeting nature of happiness, our love remains a constant.\nCherishing the ephemeral brings eternal bliss.",
            Author = "BlissSeeker",
            AuthorDesignation = "Happiness Architect",
            color = color8,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Adventurous Hearts",
            quote = "Love is the greatest adventure, filled with unexpected twists and turns.\nIn the journey of love, every challenge is a thrilling opportunity.\nAdventure awaits those who embrace the unknown together.",
            Author = "AdventureLovers",
            AuthorDesignation = "Love Explorer",
            color = color9,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Eternal Flame",
            quote = "Our love is an eternal flame, burning bright in the darkest moments.\nIn the shadows of life, our connection is a beacon of warmth.\nTrue love is a fire that never goes out.",
            Author = "FlameKeeper",
            AuthorDesignation = "Love Illuminator",
            color = color10,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Magnetic Souls",
            quote = "Two souls, like magnets, drawn together by an irresistible force.\nIn the magnetic field of love, distance is a mere illusion.\nOur connection is a cosmic dance of attraction.",
            Author = "MagneticHearts",
            AuthorDesignation = "Love Physicist",
            color = color1,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Garden of Gratitude",
            quote = "Love is a garden where gratitude blooms in every season.\nIn the soil of appreciation, our relationship thrives and blossoms.\nCultivating gratitude is the key to a flourishing love story.",
            Author = "GratitudeHarbor",
            AuthorDesignation = "Love Gardener",
            color = color2,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
        Quotes(
            title = "Celestial Harmony",
            quote = "Our love is a celestial symphony, with each moment a note in perfect harmony.\nIn the cosmic dance, we are the stars that create a radiant constellation.\nTogether, we compose a melody that resonates through the universe.",
            Author = "CelestialHarmony",
            AuthorDesignation = "Love Composer",
            color = color3,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Infinity Connection",
            quote = "Love is an infinite loop, where each moment connects seamlessly to the next.\nIn the infinity of emotions, our love is a timeless bond.\nEmbracing the endless journey together is our greatest adventure.",
            Author = "InfinitySoulmates",
            AuthorDesignation = "Love Voyager",
            color = color4,
            AuthorPic = R.drawable.j
        )
    )

    QuotesList.add(
        Quotes(
            title = "Aurora of Affection",
            quote = "Our love is an aurora, painting the sky with vibrant hues of affection.\nIn the dawn of understanding, our connection is a breathtaking display.\nThe beauty of love shines brightest in the moments of vulnerability.",
            Author = "AffectionAurora",
            AuthorDesignation = "Love Painter",
            color = color5,
            AuthorPic = R.drawable.k
        )
    )

    QuotesList.add(
        Quotes(
            title = "Whirlwind of Emotions",
            quote = "Love is a whirlwind of emotions, swirling and captivating.\nIn the storm of passion, our hearts dance to the rhythm of desire.\nThe intensity of our connection is the eye of the emotional hurricane.",
            Author = "EmotionalStorm",
            AuthorDesignation = "Love Meteorologist",
            color = color6,
            AuthorPic = R.drawable.l
        )
    )
    QuotesList.add(
        Quotes(
            title = "Sunset Serenade",
            quote = "Our love is like a sunset, painting the sky with warm hues of affection.\nIn the twilight of life, our connection is a serenade that whispers love.\nThe beauty of our love story reflects the colors of the setting sun.",
            Author = "SerenadeSeeker",
            AuthorDesignation = "Love Melodist",
            color = color1,
            AuthorPic = R.drawable.k
        )
    )

    QuotesList.add(
        Quotes(
            title = "Moonlit Promises",
            quote = "Our love is as timeless as the moon, making promises in the soft glow of night.\nIn the celestial dance, our connection is a vow that echoes through the cosmos.\nThe moonlight illuminates the path of our eternal commitment.",
            Author = "PromiseKeeper",
            AuthorDesignation = "Love Astronomer",
            color = color2,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Ocean of Emotions",
            quote = "Love is an ocean, vast and deep, where emotions ebb and flow.\nIn the tides of passion, our connection is a journey on the waves of desire.\nThe depths of our love are as boundless as the sea.",
            Author = "OceanExplorer",
            AuthorDesignation = "Love Navigator",
            color = color3,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Enchanted Forest",
            quote = "Our love is an enchanted forest, where every tree tells a tale of shared memories.\nIn the magic of togetherness, our connection is a path through the foliage of companionship.\nThe whispers of the leaves are the echoes of our love story.",
            Author = "ForestDreamer",
            AuthorDesignation = "Love Enchanter",
            color = color4,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Starry Affection",
            quote = "Our love is written in the stars, a cosmic tale of affection.\nIn the constellations of connection, every twinkle is a shared moment.\nThe universe witnesses the brilliance of our celestial bond.",
            Author = "StarryHeart",
            AuthorDesignation = "Love Astronomer",
            color = color5,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Gentle Breeze of Understanding",
            quote = "Love is a gentle breeze, carrying the whispers of understanding.\nIn the wind of empathy, our connection is a dance of shared emotions.\nThe breeze of love refreshes the landscape of our relationship.",
            Author = "BreezeBuilder",
            AuthorDesignation = "Love Wind Whisperer",
            color = color6,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Mystical Connection",
            quote = "Our love is a mystical connection, weaving a tapestry of enchantment.\nIn the magic of partnership, every spell cast is a gesture of love.\nThe mystery of our connection unfolds in the chapters of time.",
            Author = "MysticHeart",
            AuthorDesignation = "Love Magician",
            color = color7,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Rainbow of Resilience",
            quote = "Love is a rainbow of resilience, shining through the storms of life.\nIn the spectrum of emotions, our connection is a vibrant display.\nThe colors of our love are the triumphs over adversity.",
            Author = "ResilienceRainbow",
            AuthorDesignation = "Love Weathercaster",
            color = color8,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
        Quotes(
            title = "Eternal Sunrise",
            quote = "Our love is an eternal sunrise, bringing warmth to the dawn of each day.\nIn the morning of life, our connection is a radiant glow that illuminates our journey.\nThe sunrise of our love is an everlasting promise.",
            Author = "SunriseSoulmates",
            AuthorDesignation = "Love Illuminator",
            color = color9,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Whirlwind of Affection",
            quote = "Love is a whirlwind of affection, swirling and captivating.\nIn the storm of passion, our hearts dance to the rhythm of desire.\nThe intensity of our connection is the eye of the emotional hurricane.",
            Author = "AffectionWhirlwind",
            AuthorDesignation = "Love Meteorologist",
            color = color10,
            AuthorPic = R.drawable.j
        )
    )


    return QuotesList
}
fun NatureQuotesList(): MutableList<Quotes>{
    val QuotesList = mutableListOf<Quotes>()
    QuotesList.add(Quotes(
        title = "Wild Grace",
        quote = "Nature dances with wild grace, painting the world in hues of untamed beauty and serenity.",
        Author = "GracefulNature",
        AuthorDesignation = "Wild Dancer",
        color = color1,
        AuthorPic = R.drawable.l
    ))

    QuotesList.add(Quotes(
        title = "Solitude Bliss",
        quote = "In solitude, nature whispers secrets of bliss, inviting those who seek tranquility to its silent sanctuary.",
        Author = "BlissfulSolitude",
        AuthorDesignation = "Nature Seeker",
        color = color2,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Sky Canvas",
        quote = "Above, the sky becomes a canvas, where clouds and colors converge to create a masterpiece of celestial art.",
        Author = "CanvasCreator",
        AuthorDesignation = "Sky Artist",
        color = color3,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "River Song",
        quote = "Rivers sing a timeless song, their melodies flowing through landscapes, carving stories in the hearts of nature.",
        Author = "RiverMelody",
        AuthorDesignation = "Song Weaver",
        color = color4,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Forest Whispers",
        quote = "Within the forest, whispers abound—a symphony of rustling leaves and gentle breezes sharing tales of ancient wisdom.",
        Author = "WhisperingForest",
        AuthorDesignation = "Woodland Listener",
        color = color5,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Sunrise Embrace",
        quote = "At dawn, nature embraces the sun, casting a golden glow that heralds the start of a new day's enchanting journey.",
        Author = "EmbracingDawn",
        AuthorDesignation = "Sunrise Guardian",
        color = color6,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Ocean Echo",
        quote = "The ocean echoes stories untold, a vast expanse that mirrors the mysteries and wonders of the universe.",
        Author = "EchoingSeas",
        AuthorDesignation = "Ocean Chronicler",
        color = color7,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Mountain Zen",
        quote = "Mountains stand in Zen silence, teaching the art of resilience and the wisdom found in steadfast stillness.",
        Author = "ZenPeaks",
        AuthorDesignation = "Mountain Sage",
        color = color8,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Floral Elegance",
        quote = "In gardens, floral elegance unfolds—a tapestry of petals that whispers tales of beauty in every bloom.",
        Author = "ElegantBlossoms",
        AuthorDesignation = "Flower Alchemist",
        color = color9,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Sunset Palette",
        quote = "As the sun sets, the sky transforms into a vibrant palette, painting a breathtaking canvas of hues and dreams.",
        Author = "PalettePainter",
        AuthorDesignation = "Sunset Artisan",
        color = color1,
        AuthorPic = R.drawable.j
    ))


    QuotesList.add(Quotes(
        title = "Whispers of the Wind",
        quote = "In the gentle whispers of the wind, nature shares its timeless stories, carrying the secrets of the earth to those who listen.",
        Author = "NatureWhisperer",
        AuthorDesignation = "Wind Interpreter",
        color = color1,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Dance of the Leaves",
        quote = "Each leaf dances in the breeze, a choreography of nature that tells the tale of the seasons and the eternal cycle of life.",
        Author = "LeafDancer",
        AuthorDesignation = "Seasonal Choreographer",
        color = color2,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Sunrise Serenity",
        quote = "As the sun rises, it paints the sky with hues of serenity, casting a golden glow that whispers the promise of a new day.",
        Author = "SunriseHarmony",
        AuthorDesignation = "Sky Painter",
        color = color3,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Moonlit Reflections",
        quote = "In the quiet embrace of moonlight, nature reflects upon itself, unveiling a serene beauty that only the night can reveal.",
        Author = "MoonlitDreamer",
        AuthorDesignation = "Night Reflectionist",
        color = color4,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Rivers of Resilience",
        quote = "Rivers flow with an unwavering resilience, carving through mountains and valleys—a testament to nature's persistence and adaptability.",
        Author = "RiverResilience",
        AuthorDesignation = "Flow Navigator",
        color = color5,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Majestic Mountains",
        quote = "Mountains stand as silent sentinels, their majestic peaks reaching for the sky. In their grandeur, they teach us the power of steadfastness.",
        Author = "MountainMajesty",
        AuthorDesignation = "Peak Guardian",
        color = color6,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Floral Symphony",
        quote = "Each flower is a note in nature's symphony, a melody of colors and fragrances that harmonize in the garden of life.",
        Author = "FloralComposer",
        AuthorDesignation = "Blossom Maestro",
        color = color7,
        AuthorPic = R.drawable.l
    ))

    QuotesList.add(Quotes(
        title = "Beneath the Canopy",
        quote = "Under the canopy of trees, a sanctuary of tranquility unfolds. The leaves above whisper tales of ancient wisdom to those who seek solace.",
        Author = "CanopySeeker",
        AuthorDesignation = "Tree Whisperer",
        color = color8,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Ocean's Lullaby",
        quote = "The ocean's lullaby echoes through the waves, a soothing serenade that cradles the world in the arms of its vast and timeless embrace.",
        Author = "OceanLullaby",
        AuthorDesignation = "Wave Composer",
        color = color9,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Sunset Reverie",
        quote = "As the sun sets, it paints the sky with a canvas of dreams, casting a warm glow that invites reflection on the beauty of endings and new beginnings.",
        Author = "SunsetDreamer",
        AuthorDesignation = "Sky Dreamweaver",
        color = color10,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Misty Mornings",
        quote = "In misty mornings, nature unveils its secrets, shrouding the world in a gentle embrace of ethereal beauty and quiet wonder.",
        Author = "MistyDreamer",
        AuthorDesignation = "Morning Whisperer",
        color = color1,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Cascade Harmony",
        quote = "Water cascades in a symphony of harmony, a dance of liquid notes that echo the rhythm of nature's eternal melody.",
        Author = "CascadeComposer",
        AuthorDesignation = "Water Maestro",
        color = color2,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Crimson Horizon",
        quote = "As the sun sets, the horizon blushes in shades of crimson, a breathtaking spectacle that paints the world in warm hues of farewell.",
        Author = "CrimsonPainter",
        AuthorDesignation = "Horizon Artisan",
        color = color3,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Golden Fields",
        quote = "Fields of gold sway in the breeze, a golden tapestry that whispers tales of abundance, prosperity, and the dance of the harvest.",
        Author = "GoldenHarvester",
        AuthorDesignation = "Fields Keeper",
        color = color4,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Lunar Lullaby",
        quote = "The moon sings a lullaby to the night, a celestial serenade that cradles the world in a gentle embrace of silver and dreams.",
        Author = "LunarLuller",
        AuthorDesignation = "Night Serenader",
        color = color5,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Sapphire Seas",
        quote = "Beyond the shore, sapphire seas stretch into the horizon, a boundless expanse that whispers the tales of aquatic adventures.",
        Author = "SapphireSailor",
        AuthorDesignation = "Sea Explorer",
        color = color6,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Verdant Symphony",
        quote = "Nature conducts a verdant symphony, where the rustle of leaves, the chirping of birds, and the hum of insects blend into a melodious orchestra.",
        Author = "VerdantComposer",
        AuthorDesignation = "Nature Maestro",
        color = color7,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Silent Desert",
        quote = "In the silent desert, nature sculpts dunes of serenity—a vast expanse where time stands still, and the wind whispers ancient tales.",
        Author = "DesertSculpter",
        AuthorDesignation = "Sand Sage",
        color = color8,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Amber Canopy",
        quote = "Under an amber canopy, autumn unfolds its magic—a tapestry of fiery hues that weave tales of change, transition, and beauty in letting go.",
        Author = "AmberWeaver",
        AuthorDesignation = "Autumn Artisan",
        color = color9,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Celestial Harmony",
        quote = "In the celestial dome, stars align in perfect harmony—a cosmic dance that mirrors the beauty of unity in the vastness of the universe.",
        Author = "CelestialHarmonist",
        AuthorDesignation = "Star Choreographer",
        color = color10,
        AuthorPic = R.drawable.l
    ))
    QuotesList.add(Quotes(
        title = "Velvet Twilight",
        quote = "As twilight descends, the sky dons a velvet cloak, embracing the world in soft shadows and whispers of dreams yet to unfold.",
        Author = "TwilightDreamer",
        AuthorDesignation = "Velvet Guardian",
        color = color1,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Ephemeral Blossoms",
        quote = "Blossoms bloom in ephemeral splendor, a fleeting dance that paints the landscape in the delicate hues of nature's transient beauty.",
        Author = "BlossomPainter",
        AuthorDesignation = "Ephemeral Artist",
        color = color2,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Azure Horizon",
        quote = "On the azure horizon, the sky meets the sea in an eternal embrace, a boundless canvas where dreams take flight with the wings of hope.",
        Author = "AzureDreamer",
        AuthorDesignation = "Horizon Dreamweaver",
        color = color3,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Rustic Meadows",
        quote = "In rustic meadows, wildflowers sway, narrating tales of resilience and simplicity—a story told through the vibrant palette of nature's artistry.",
        Author = "MeadowStoryteller",
        AuthorDesignation = "Floral Narrator",
        color = color4,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Whirlwind Whispers",
        quote = "Whirlwinds whisper secrets of change, swirling through the air as nature's reminder that transformation is an elemental part of the dance of life.",
        Author = "WhirlwindScribe",
        AuthorDesignation = "Change Whisperer",
        color = color5,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Ember Serenade",
        quote = "In the crackling embers of a fire, nature composes a serenade—a melody that echoes the warmth of connection and the hearth of shared moments.",
        Author = "EmberComposer",
        AuthorDesignation = "Fire Serenader",
        color = color6,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Lunar Canvas",
        quote = "The moon paints a canvas with silvery strokes, casting a luminous glow that transforms the night into a masterpiece of celestial artistry.",
        Author = "LunarArtist",
        AuthorDesignation = "Night Canvas Painter",
        color = color7,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Whispering Zephyrs",
        quote = "Zephyrs whisper through leaves, weaving tales of gentle breezes that carry the essence of nature's poetry to those who listen with open hearts.",
        Author = "ZephyrPoet",
        AuthorDesignation = "Breeze Bard",
        color = color8,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Iridescent Waters",
        quote = "Waters shimmer in iridescence, a dance of reflections that mirrors the ever-changing moods and mysteries of the liquid realms.",
        Author = "AquaDancer",
        AuthorDesignation = "Water Choreographer",
        color = color9,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Celestial Bloom",
        quote = "Amidst the celestial bloom, stars become flowers in the garden of the night, each one a radiant petal in the cosmic tapestry of the universe.",
        Author = "StellarGardener",
        AuthorDesignation = "Celestial Florist",
        color = color10,
        AuthorPic = R.drawable.l
    ))

    return QuotesList
}
fun ImaginationQuotesList(): MutableList<Quotes> {
    val QuotesList = mutableListOf<Quotes>()
    Quotes(
        title = "Vivid Whispers",
        quote = "Imagination whispers in vivid hues, painting the mind with tales that dance to the rhythm of creativity and the melody of dreams.",
        Author = "VividWhisperer",
        AuthorDesignation = "Colorful Muse",
        color = color1,
        AuthorPic = R.drawable.b
    )

    Quotes(
        title = "Ethereal Echo",
        quote = "In the ethereal realm of imagination, every idea is an echo that reverberates through the corridors of creativity, resonating with endless possibilities.",
        Author = "EtherealEchoer",
        AuthorDesignation = "Echo Weaver",
        color = color2,
        AuthorPic = R.drawable.c
    )

    Quotes(
        title = "Luminary Dream",
        quote = "Imagination is the luminary that lights the path to dreams, guiding the mind through the celestial landscapes of endless inspiration.",
        Author = "LuminaryDreamer",
        AuthorDesignation = "Dream Guide",
        color = color3,
        AuthorPic = R.drawable.d
    )

    Quotes(
        title = "Infinite Palette",
        quote = "Within the infinite palette of imagination, thoughts are the colors that blend and mix, creating the masterpiece of a boundless creative mind.",
        Author = "PaletteMixer",
        AuthorDesignation = "Infinite Artist",
        color = color4,
        AuthorPic = R.drawable.b
    )

    Quotes(
        title = "Arcane Vision",
        quote = "Imagination holds an arcane vision, unveiling the hidden landscapes of the mind and revealing the mystical realms of untold stories.",
        Author = "ArcaneVisionary",
        AuthorDesignation = "Visionary",
        color = color5,
        AuthorPic = R.drawable.c
    )

    Quotes(
        title = "Celestial Harmonics",
        quote = "Imagination conducts the celestial harmonics, orchestrating the symphony of ideas that resonate through the cosmic expanse of the creative mind.",
        Author = "CelestialHarmonist",
        AuthorDesignation = "Harmony Conductor",
        color = color6,
        AuthorPic = R.drawable.d
    )

    Quotes(
        title = "Ephemeral Odyssey",
        quote = "Imagination embarks on an ephemeral odyssey, traversing the landscapes of the mind and discovering the fleeting wonders of creativity.",
        Author = "OdysseyTraveler",
        AuthorDesignation = "Ephemeral Voyager",
        color = color7,
        AuthorPic = R.drawable.e
    )

    Quotes(
        title = "Surreal Visions",
        quote = "In the surreal realm of imagination, visions unfold like dreams, blurring the boundaries between reality and the fantastical landscapes of creativity.",
        Author = "SurrealVisionary",
        AuthorDesignation = "Surreal Dreamer",
        color = color8,
        AuthorPic = R.drawable.g
    )

    Quotes(
        title = "Whimsical Serenade",
        quote = "Imagination serenades with whimsy, composing a playful melody that dances through the thoughts and captures the heart with laughter and joy.",
        Author = "WhimsicalSerenader",
        AuthorDesignation = "Playful Composer",
        color = color9,
        AuthorPic = R.drawable.h
    )

    Quotes(
        title = "Dreamscape Oracle",
        quote = "The imagination is a dreamscape oracle, revealing visions that guide the mind through the mystical landscapes of boundless creativity.",
        Author = "DreamscapeSeer",
        AuthorDesignation = "Oracle Navigator",
        color = color10,
        AuthorPic = R.drawable.i
    )

    Quotes(
        title = "Ethereal Tapestry",
        quote = "Imagination weaves an ethereal tapestry, a fabric of dreams and ideas that unfolds with every thought, creating a vision of boundless creativity.",
        Author = "TapestryWeaver",
        AuthorDesignation = "Ethereal Artisan",
        color = color1,
        AuthorPic = R.drawable.j
    )

    Quotes(
        title = "Luminous Vision",
        quote = "Imagination is the luminous vision that illuminates the mind, casting a radiant glow on the canvas of creativity and inspiring endless possibilities.",
        Author = "LuminousDreamer",
        AuthorDesignation = "Radiant Visionary",
        color = color2,
        AuthorPic = R.drawable.k
    )

    Quotes(
        title = "Whispered Whimsy",
        quote = "In the whispered whimsy of imagination, every idea is a playful secret that dances through the mind, inviting joy and laughter into the creative space.",
        Author = "WhimsicalWhisperer",
        AuthorDesignation = "Playful Seer",
        color = color3,
        AuthorPic = R.drawable.l
    )

    Quotes(
        title = "Celestial Palette",
        quote = "Imagination unfolds a celestial palette, where thoughts become colors that paint the skies of creativity with the hues of boundless inspiration.",
        Author = "CelestialPainter",
        AuthorDesignation = "Cosmic Artist",
        color = color4,
        AuthorPic = R.drawable.c
    )

    Quotes(
        title = "Arcane Alchemy",
        quote = "In the arcane laboratory of imagination, thoughts transform into alchemy, blending and fusing to create the elixir of boundless creativity.",
        Author = "ArcaneAlchemist",
        AuthorDesignation = "Alchemy Weaver",
        color = color5,
        AuthorPic = R.drawable.b
    )

    Quotes(
        title = "Whispering Winds",
        quote = "Imagination rides on the whispering winds, carrying dreams and ideas to distant lands where creativity dances with the breezy cadence of possibility.",
        Author = "WindsweptDreamer",
        AuthorDesignation = "Wind Whisperer",
        color = color6,
        AuthorPic = R.drawable.j
    )

    Quotes(
        title = "Quantum Dreamer",
        quote = "Imagination explores the quantum realm of dreams, where every idea is a particle of possibility, entangled in the vast fabric of creative reality.",
        Author = "QuantumDreamer",
        AuthorDesignation = "Quantum Explorer",
        color = color7,
        AuthorPic = R.drawable.b
    )

    QuotesList.add(Quotes(
        title = "Infinity Illumination",
        quote = "In the infinity of imagination, each idea is a spark that illuminates the creative space, casting a radiant glow on the endless expanse of possibility.",
        Author = "InfinityIlluminator",
        AuthorDesignation = "Infinite Visionary",
        color = color8,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Whispered Echoes",
        quote = "In the whispered echoes of imagination, ideas reverberate through the corridors of creativity, leaving traces of inspiration that linger in the mind.",
        Author = "EchoWhisperer",
        AuthorDesignation = "Creative Echo",
        color = color10,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Dream Weaver",
        quote = "Imagination is the loom that weaves dreams into the fabric of reality, creating a tapestry of limitless possibilities.",
        Author = "DreamSmith",
        AuthorDesignation = "Weaver",
        color = color1,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Cosmic Ponder",
        quote = "In the vast canvas of imagination, thoughts become galaxies, and ideas are the stars that illuminate the cosmic expanse of creativity.",
        Author = "CosmicMind",
        AuthorDesignation = "Ponderer",
        color = color2,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Wonders Within",
        quote = "Imagination unlocks the door to a realm where wonders reside within every thought, waiting to be discovered by those who dare to dream.",
        Author = "WondersSeeker",
        AuthorDesignation = "Dreamer",
        color = color3,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Mindscape Muse",
        quote = "The imagination is a mindscape where creativity dances to the rhythm of ideas, painting a kaleidoscope of inspiration in endless hues.",
        Author = "MindscapePainter",
        AuthorDesignation = "Muse",
        color = color4,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Enigma Echo",
        quote = "Imagination echoes the enigma of unseen worlds, whispering secrets that linger in the spaces between reality and the realms of the unknown.",
        Author = "EnigmaWhisper",
        AuthorDesignation = "Echo",
        color = color5,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Infinity Sketch",
        quote = "In the infinity of imagination, each idea is a sketch on the canvas of the mind, waiting to be brought to life in the gallery of creativity.",
        Author = "InfinitySketcher",
        AuthorDesignation = "Sketch",
        color = color6,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Ethereal Vision",
        quote = "Imagination is the ethereal lens through which we perceive the unseen, transforming the ordinary into the extraordinary with every thought.",
        Author = "EtherealSeer",
        AuthorDesignation = "Vision",
        color = color7,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Fantasy Alchemy",
        quote = "In the alchemy of imagination, fantasies become potions that enchant the soul, transforming reality into a magical tapestry of dreams.",
        Author = "FantasyAlchemist",
        AuthorDesignation = "Alchemy",
        color = color8,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Whimsy Oracle",
        quote = "Imagination is the whimsy oracle that foretells tales yet to unfold, inviting us to explore the realms of limitless possibility and wonder.",
        Author = "WhimsySeer",
        AuthorDesignation = "Oracle",
        color = color9,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Mind Canvas",
        quote = "The imagination is a mind canvas where thoughts paint portraits of imagination, capturing the essence of creativity in strokes of infinite colors.",
        Author = "CanvasPainter",
        AuthorDesignation = "Canvas",
        color = color10,
        AuthorPic = R.drawable.l
    ))

    return QuotesList
}
fun FriendshipQuotesList(): MutableList<Quotes>{
    val QuotesList = mutableListOf<Quotes>()
    QuotesList.add(Quotes(
        title = "Kindred Souls",
        quote = "Friendship is the union of kindred souls, a bond that weaves laughter, shared stories, and unconditional support into the fabric of life.",
        Author = "SoulMate",
        AuthorDesignation = "Kindred Spirit",
        color = color1,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Eternal Pals",
        quote = "In the tapestry of time, friendships are eternal threads that weave through the moments, creating a colorful masterpiece of shared joy and memories.",
        Author = "TimelessBuddy",
        AuthorDesignation = "Eternal Companion",
        color = color2,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Joyful Bonds",
        quote = "Friendship forms joyful bonds, a symphony of laughter and understanding that plays in the heart, echoing the melody of genuine connection.",
        Author = "JoyfulHarmony",
        AuthorDesignation = "Laughter Maestro",
        color = color3,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "True Comrades",
        quote = "True friendship is the alliance of kindred spirits, comrades who walk side by side through the journey of life, sharing triumphs and overcoming challenges together.",
        Author = "ComradeHeart",
        AuthorDesignation = "True Companion",
        color = color4,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Radiant Bonds",
        quote = "Friendship radiates like sunlight, casting a warm glow that brightens even the darkest corners of life, creating a space filled with shared warmth and affection.",
        Author = "RadiantPal",
        AuthorDesignation = "Sunshine Companion",
        color = color5,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Caring Allies",
        quote = "In the battlefield of life, friendships are the caring allies who stand by your side, offering support, understanding, and the strength to face challenges.",
        Author = "AllyHeart",
        AuthorDesignation = "Caring Companion",
        color = color6,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Cherished Bonds",
        quote = "Friendships are cherished bonds, like rare gems that sparkle in the treasure chest of the heart, each one unique and irreplaceable.",
        Author = "CherishedGem",
        AuthorDesignation = "Treasure Keeper",
        color = color7,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Loyal Allies",
        quote = "Loyalty is the foundation of true friendship, a bond that withstands the tests of time and remains steadfast through the seasons of life.",
        Author = "LoyalFriend",
        AuthorDesignation = "Trustworthy Companion",
        color = color8,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Heartfelt Kinship",
        quote = "Friendship is a heartfelt kinship, a connection that goes beyond words, where understanding and support flow effortlessly between kindred spirits.",
        Author = "HeartfeltBuddy",
        AuthorDesignation = "Kindred Companion",
        color = color9,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Enduring Bonds",
        quote = "True friendships are enduring bonds, resilient like roots that anchor the tree of life, weathering storms and blossoming in the sunlight of shared moments.",
        Author = "EnduringRoots",
        AuthorDesignation = "Resilient Companion",
        color = color10,
        AuthorPic = R.drawable.l
    ))

    QuotesList.add(Quotes(
        title = "Harmony Duet",
        quote = "Friendship is a harmonious duet, where two hearts sing the melody of laughter, share the verses of understanding, and create a song that echoes through time.",
        Author = "HarmonySinger",
        AuthorDesignation = "Melody Maker",
        color = color1,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Benevolent Bonds",
        quote = "In the garden of friendship, bonds bloom like flowers, each one a testament to the benevolence that nurtures the soul and fills life with fragrance.",
        Author = "BenevolentBloom",
        AuthorDesignation = "Garden Keeper",
        color = color2,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Genuine Unity",
        quote = "Friendship is the genuine unity of hearts, where differences are celebrated, and the symphony of connection plays a melody of acceptance and love.",
        Author = "UnityHeart",
        AuthorDesignation = "Symphony Conductor",
        color = color3,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Jovial Bonds",
        quote = "Jovial friendships are like a carnival of joy, where laughter is the main attraction, and every shared moment becomes a thrilling ride of happiness.",
        Author = "JovialCraze",
        AuthorDesignation = "Joyful Companion",
        color = color4,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "United Spirits",
        quote = "Friendship unites spirits, creating a tapestry of shared dreams, laughter, and support that weaves through the journey of life, making it more vibrant and meaningful.",
        Author = "UnitedSoul",
        AuthorDesignation = "Spirit Weaver",
        color = color5,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Lifelong Allies",
        quote = "True friendships are lifelong allies, companions who walk the path of life together, sharing the highs and lows, and creating a story of enduring camaraderie.",
        Author = "LifelongComrade",
        AuthorDesignation = "Companion Navigator",
        color = color6,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Affectionate Bonds",
        quote = "Friendship is a garden of affectionate bonds, where every shared moment is a blossom that adds color to the landscape of companionship.",
        Author = "AffectionateBloom",
        AuthorDesignation = "Blossom Gardener",
        color = color7,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Trustful Unity",
        quote = "Trust is the cornerstone of friendship, a foundation that builds a tower of unity, resilience, and unwavering support between kindred hearts.",
        Author = "TrustfulTower",
        AuthorDesignation = "Unity Builder",
        color = color8,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Companionship Symphony",
        quote = "Friendship is a symphony of companionship, where hearts play the notes of trust, laughter, and understanding, creating a melodious masterpiece of shared moments.",
        Author = "SymphonyComrade",
        AuthorDesignation = "Harmony Composer",
        color = color9,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Epic Alliances",
        quote = "Friendships create epic alliances, where allies stand together through the chapters of life, writing a saga of shared adventures and enduring camaraderie.",
        Author = "EpicAlliance",
        AuthorDesignation = "Saga Scribbler",
        color = color10,
        AuthorPic = R.drawable.i
    ))


    return QuotesList
}
fun PositiveQuotesList(): MutableList<Quotes>{
    val QuotesList = mutableListOf<Quotes>()

    QuotesList.add(Quotes(
        title = "Radiant Sun",
        quote = "In the canvas of life, let your positivity paint the sky with the radiant hues of hope, joy, and boundless possibilities.",
        Author = "SunPainter",
        AuthorDesignation = "Positivity Artist",
        color = color1,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Optimistic Dawn",
        quote = "Every dawn is an optimistic canvas, inviting you to paint your day with the vibrant strokes of gratitude, optimism, and resilience.",
        Author = "DawnOptimist",
        AuthorDesignation = "Gratitude Painter",
        color = color2,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Positivity Prism",
        quote = "Life is a positivity prism; let the light of your positive thoughts refract into a spectrum of optimism, kindness, and endless possibilities.",
        Author = "PrismOptimist",
        AuthorDesignation = "Positive Refractor",
        color = color3,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Sunny Outlook",
        quote = "Embrace a sunny outlook on life; let your positivity shine through the clouds of challenges, illuminating the path with the warmth of optimism.",
        Author = "SunnyOptimist",
        AuthorDesignation = "Sunshine Enthusiast",
        color = color4,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Vibrant Mindset",
        quote = "Cultivate a vibrant mindset; let your thoughts bloom into flowers of positivity, resilience, and unwavering belief in your own potential.",
        Author = "VibrantMind",
        AuthorDesignation = "Mindset Gardener",
        color = color5,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Hopeful Horizons",
        quote = "Look beyond the horizon with hope; positivity is the compass that guides you to the vast landscapes of dreams, possibilities, and fulfillment.",
        Author = "HopefulExplorer",
        AuthorDesignation = "Horizon Voyager",
        color = color6,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Joyful Symphony",
        quote = "Life is a joyful symphony; let your positive actions play the notes of kindness, gratitude, and love, creating a melody of harmony and happiness.",
        Author = "JoyfulHarmonist",
        AuthorDesignation = "Symphony Conductor",
        color = color7,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Optimism Echo",
        quote = "Let optimism echo in your thoughts; positive affirmations are the ripples that spread across the lake of life, creating a serene and uplifting atmosphere.",
        Author = "EchoOptimist",
        AuthorDesignation = "Affirmation Weaver",
        color = color8,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Positive Aura",
        quote = "Cultivate a positive aura; let your presence radiate the uplifting energy of optimism, inspiring those around you to embrace hope and joy.",
        Author = "AuraOptimist",
        AuthorDesignation = "Positive Radiator",
        color = color9,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Gratitude Glow",
        quote = "Nurture the gratitude glow within; positivity is the spark that ignites a flame of thankfulness, warming your heart and brightening your perspective.",
        Author = "GratitudeFlame",
        AuthorDesignation = "Glow Keeper",
        color = color10,
        AuthorPic = R.drawable.l
    ))

    QuotesList.add(Quotes(
        title = "Inspiration Beacon",
        quote = "Be an inspiration beacon; let your positive actions shine like a lighthouse, guiding others through the seas of challenges towards the shores of success.",
        Author = "BeaconOptimist",
        AuthorDesignation = "Lighthouse Keeper",
        color = color1,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Positivity Oasis",
        quote = "Create a positivity oasis in your mind; let the waters of optimism, kindness, and self-love flow, nurturing a flourishing garden of joy and well-being.",
        Author = "OasisOptimist",
        AuthorDesignation = "Garden Tender",
        color = color2,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Uplifting Breeze",
        quote = "Be the uplifting breeze; positivity is the wind that carries seeds of hope, planting the flowers of joy and optimism in the garden of your life.",
        Author = "BreezeOptimist",
        AuthorDesignation = "Wind Gardener",
        color = color3,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Radiant Energy",
        quote = "Radiate positive energy; let your thoughts be the rays that brighten the world, creating a sunny atmosphere of optimism and encouragement.",
        Author = "EnergyOptimist",
        AuthorDesignation = "Sunbeam Generator",
        color = color4,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Optimistic Canvas",
        quote = "Life is an optimistic canvas; paint it with the colors of positive thoughts, resilience, and the masterpiece of your dreams.",
        Author = "CanvasOptimist",
        AuthorDesignation = "Dream Artist",
        color = color5,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Sunlit Mind",
        quote = "Cultivate a sunlit mind; let positivity be the sunlight that nourishes the garden of your thoughts, bringing warmth and brightness to your inner world.",
        Author = "SunlitOptimist",
        AuthorDesignation = "Mind Gardener",
        color = color6,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Hopeful Whispers",
        quote = "Listen to the whispers of hope; positivity is the gentle breeze that carries uplifting messages, encouraging you to embrace a future filled with possibilities.",
        Author = "WhisperOptimist",
        AuthorDesignation = "Hopeful Whisperer",
        color = color7,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Grateful Heart",
        quote = "Cultivate a grateful heart; let positivity be the fertile soil where seeds of gratitude bloom, creating a garden of joy and contentment.",
        Author = "GratefulOptimist",
        AuthorDesignation = "Heart Gardener",
        color = color8,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Positivity Spark",
        quote = "Ignite the positivity spark within; let it be the flame that lights up the darkness, dispelling negativity and illuminating the path to joy.",
        Author = "SparkOptimist",
        AuthorDesignation = "Flame Igniter",
        color = color9,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Optimism Cascade",
        quote = "Let optimism cascade like a waterfall; positivity is the flowing stream that washes away worries, leaving behind a refreshing pool of hope and tranquility.",
        Author = "CascadeOptimist",
        AuthorDesignation = "Waterfall Weaver",
        color = color10,
        AuthorPic = R.drawable.i
    ))
    QuotesList.add(Quotes(
        title = "Joyful Melody",
        quote = "Life is a joyful melody; let positivity be the music that resonates through your days, creating harmonious moments of happiness and gratitude.",
        Author = "MelodyOptimist",
        AuthorDesignation = "Harmony Composer",
        color = color1,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Eternal Sunshine",
        quote = "Cultivate an eternal sunshine within; let positivity be the radiant sun that bathes your soul in warmth, casting away shadows and illuminating your inner world.",
        Author = "SunshineOptimist",
        AuthorDesignation = "Eternal Radiator",
        color = color2,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Blissful Echo",
        quote = "Life echoes with bliss; let positivity be the reverberation that resonates through your being, creating a symphony of joy and contentment.",
        Author = "EchoOptimist",
        AuthorDesignation = "Blissful Harmonist",
        color = color3,
        AuthorPic = R.drawable.l
    ))

    QuotesList.add(Quotes(
        title = "Uplifted Spirit",
        quote = "Nurture an uplifted spirit; let positivity be the gentle breeze that lifts your soul, carrying it to the heights of optimism and inspiration.",
        Author = "SpiritOptimist",
        AuthorDesignation = "Soul Lifter",
        color = color4,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Hopeful Radiance",
        quote = "Radiate hopeful radiance; let positivity be the light that shines from within, dispelling darkness and illuminating the path to a brighter future.",
        Author = "RadiantOptimist",
        AuthorDesignation = "Hopeful Illuminator",
        color = color5,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Euphoric Bloom",
        quote = "Life is an euphoric bloom; let positivity be the petals that unfold, revealing the beauty of each moment and the endless possibilities it holds.",
        Author = "BloomOptimist",
        AuthorDesignation = "Euphoria Gardener",
        color = color6,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Blissful Whispers",
        quote = "Listen to blissful whispers; let positivity be the gentle breeze that carries messages of joy, love, and peace, creating an atmosphere of serenity.",
        Author = "WhisperOptimist",
        AuthorDesignation = "Blissful Whisperer",
        color = color7,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Inspired Glow",
        quote = "Ignite the inspired glow within; let positivity be the flame that sparks creativity, innovation, and a radiant enthusiasm for life's endless possibilities.",
        Author = "GlowOptimist",
        AuthorDesignation = "Inspiration Igniter",
        color = color8,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Serenity Cascade",
        quote = "Let serenity cascade like a waterfall; positivity is the flowing stream that washes away stress, leaving behind a tranquil pool of peace and well-being.",
        Author = "CascadeOptimist",
        AuthorDesignation = "Serenity Weaver",
        color = color9,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Harmony Beacon",
        quote = "Be a harmony beacon; let positivity be the light that guides others through the waves of life, creating a symphony of shared joy and well-being.",
        Author = "BeaconOptimist",
        AuthorDesignation = "Harmony Guide",
        color = color10,
        AuthorPic = R.drawable.e
    ))

    return QuotesList
}
fun SuccessQuotesList(): MutableList<Quotes>{
    val QuotesList = mutableListOf<Quotes>()
    QuotesList.add(Quotes(
        title = "Triumphant Journey",
        quote = "Success is a triumphant journey, each step a testament to perseverance, determination, and the unwavering pursuit of one's dreams.",
        Author = "JourneyAchiever",
        AuthorDesignation = "Triumph Navigator",
        color = color1,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Victorious Ascent",
        quote = "The path to success is a victorious ascent, each climb a conquest of challenges, pushing boundaries, and reaching new summits of achievement.",
        Author = "AscentConqueror",
        AuthorDesignation = "Victory Pioneer",
        color = color2,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Momentum Mastery",
        quote = "Success is momentum mastery, a dance with persistence, resilience, and the rhythm of continuous effort propelling dreams to reality.",
        Author = "MomentumMaster",
        AuthorDesignation = "Success Choreographer",
        color = color3,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Triumph Tales",
        quote = "In the book of success, every chapter is filled with triumph tales, written with the ink of dedication, passion, and the pursuit of excellence.",
        Author = "TalesAchiever",
        AuthorDesignation = "Triumph Author",
        color = color4,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Achievement Symphony",
        quote = "Success is an achievement symphony, where each note is a milestone, resonating with dedication, hard work, and the harmony of a well-lived journey.",
        Author = "SymphonyAchiever",
        AuthorDesignation = "Harmony Composer",
        color = color5,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Pinnacle Pursuit",
        quote = "The pursuit of success is reaching the pinnacle, a summit attained through passion, focus, and the unyielding climb against the winds of challenges.",
        Author = "PinnaclePursuer",
        AuthorDesignation = "Summit Seeker",
        color = color6,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Triumphant Echoes",
        quote = "Success echoes triumphantly, reverberating through the corridors of hard work, perseverance, and the unwavering belief in the possibility of greatness.",
        Author = "EchoTriumpher",
        AuthorDesignation = "Triumph Whisperer",
        color = color7,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Conquest Canvas",
        quote = "Success is a conquest canvas, painted with the bold strokes of ambition, resilience, and the vivid colors of achievements that adorn the masterpiece.",
        Author = "CanvasConqueror",
        AuthorDesignation = "Conquest Artist",
        color = color8,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Victory Vision",
        quote = "The vision of success is a victory, a realization forged with clarity, determination, and the unwavering gaze fixed on the horizon of achievement.",
        Author = "VisionVictor",
        AuthorDesignation = "Victory Seer",
        color = color9,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Triumph Trail",
        quote = "Success leaves a triumph trail, a path marked with footprints of hard work, dedication, and the unwavering commitment to one's journey of excellence.",
        Author = "TrailTriumpher",
        AuthorDesignation = "Triumph Trailblazer",
        color = color10,
        AuthorPic = R.drawable.l
    ))

    QuotesList.add(Quotes(
        title = "Champion's Symphony",
        quote = "Success is the champion's symphony, a melody composed with the beats of resilience, dedication, and the triumphant rhythm of overcoming challenges.",
        Author = "SymphonyChampion",
        AuthorDesignation = "Champion Composer",
        color = color1,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Elevation Echoes",
        quote = "Success echoes at the elevation, resonating through the peaks of determination, ambition, and the continuous climb towards greater achievements.",
        Author = "EchoElevator",
        AuthorDesignation = "Elevation Whisperer",
        color = color2,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Triumph Tunes",
        quote = "Success is a symphony of triumph tunes, where each note is a victory, played with the instruments of effort, persistence, and the rhythm of accomplishment.",
        Author = "TunesTriumpher",
        AuthorDesignation = "Triumph Composer",
        color = color3,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Pinnacle Prowess",
        quote = "Prowess at the pinnacle is success, a summit conquered with skill, perseverance, and the mastery of navigating the challenging heights of achievement.",
        Author = "ProwessPioneer",
        AuthorDesignation = "Pinnacle Master",
        color = color4,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Achievement Aura",
        quote = "Success radiates an achievement aura, an energy field charged with the positive vibes of hard work, dedication, and the joy of accomplishment.",
        Author = "AuraAchiever",
        AuthorDesignation = "Achievement Radiator",
        color = color5,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Triumphant Trails",
        quote = "Triumphant trails lead to success, pathways marked with the imprints of perseverance, resilience, and the indomitable spirit of a relentless journey.",
        Author = "TrailsTriumpher",
        AuthorDesignation = "Triumph Trailblazer",
        color = color6,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Victorious Visions",
        quote = "Success is a tapestry woven with victorious visions, threads spun from dreams, determination, and the unwavering commitment to achieving greatness.",
        Author = "VisionVictor",
        AuthorDesignation = "Victory Weaver",
        color = color7,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Triumph Tidings",
        quote = "Triumph tidings announce success, heralding the arrival of achievement with the trumpets of hard work, dedication, and the joy of accomplishment.",
        Author = "TidingsTriumpher",
        AuthorDesignation = "Triumph Herald",
        color = color8,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Epic Elevation",
        quote = "Success is an epic elevation, a climb forged with determination, resilience, and the conquering spirit that propels one to new heights of achievement.",
        Author = "EpicElevator",
        AuthorDesignation = "Elevation Conqueror",
        color = color9,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Triumph Symphony",
        quote = "Success is a symphony of triumph, where each movement is a victory, played with the instruments of ambition, persistence, and the rhythm of accomplishment.",
        Author = "SymphonyTriumpher",
        AuthorDesignation = "Triumph Composer",
        color = color10,
        AuthorPic = R.drawable.l
    ))
    QuotesList.add(Quotes(
        title = "Conquest Crescendo",
        quote = "Success is a conquest crescendo, each note a victory, building a symphony of accomplishment with the instruments of dedication and resilience.",
        Author = "CrescendoConqueror",
        AuthorDesignation = "Victory Composer",
        color = color1,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Triumph Trailblaze",
        quote = "Triumph is a trailblazing journey, forging a path through challenges, fueled by the fires of determination, ambition, and the pursuit of greatness.",
        Author = "TrailblazeTriumpher",
        AuthorDesignation = "Triumph Pathfinder",
        color = color2,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Elevation Euphoria",
        quote = "Success is an elevation euphoria, the exhilarating feeling of reaching new heights through the climb of persistence, passion, and relentless effort.",
        Author = "EuphoriaElevator",
        AuthorDesignation = "Elevation Enthusiast",
        color = color3,
        AuthorPic = R.drawable.l
    ))

    QuotesList.add(Quotes(
        title = "Triumphant Symphony",
        quote = "Success is a triumphant symphony, played with the instruments of discipline, determination, and the harmonious notes of achieving greatness.",
        Author = "SymphonyTriumpher",
        AuthorDesignation = "Victory Composer",
        color = color4,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Pinnacle Pursuit",
        quote = "The pursuit of success is the climb to the pinnacle, where each step is a victory, and the view is adorned with the landscapes of achievement.",
        Author = "PursuitPioneer",
        AuthorDesignation = "Summit Seeker",
        color = color5,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Triumph Tapestry",
        quote = "Success weaves a triumph tapestry, threads spun from effort, resilience, and the beautiful patterns of achievements that adorn the masterpiece of life.",
        Author = "TapestryTriumpher",
        AuthorDesignation = "Triumph Weaver",
        color = color6,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Epic Elevation",
        quote = "Success is an epic elevation, a climb forged with determination, resilience, and the conquering spirit that propels one to new heights of achievement.",
        Author = "EpicElevator",
        AuthorDesignation = "Elevation Conqueror",
        color = color7,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Triumphant Trail",
        quote = "The trail of triumph is a journey marked by footprints of hard work, perseverance, and the relentless pursuit of goals, leading to the summit of success.",
        Author = "TrailTriumpher",
        AuthorDesignation = "Triumph Explorer",
        color = color8,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Victory Voyage",
        quote = "Success is a victorious voyage, sailing through the seas of challenges, guided by the compass of ambition, and reaching the shores of triumph.",
        Author = "VoyageVictor",
        AuthorDesignation = "Triumph Sailor",
        color = color9,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Triumphant Echoes",
        quote = "Triumphant echoes resound in the corridors of achievement, reverberating with the triumphs of dedication, perseverance, and the pursuit of excellence.",
        Author = "EchoTriumpher",
        AuthorDesignation = "Triumph Whisperer",
        color = color10,
        AuthorPic = R.drawable.e
    ))

    return QuotesList
}
fun HealthQuotesList(): MutableList<Quotes>{
    val QuotesList = mutableListOf<Quotes>()

    QuotesList.add(Quotes(
        title = "Vitality Boost",
        quote = "Nourish your body and soul; vitality is the key to unlocking a life of energy, wellness, and enduring health.",
        Author = "BoostHealer",
        AuthorDesignation = "Vitality Advocate",
        color = color1,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Wellness Symphony",
        quote = "Life is a wellness symphony; let your daily choices play the harmonious notes of nutrition, exercise, and mental well-being.",
        Author = "SymphonyHealer",
        AuthorDesignation = "Wellness Composer",
        color = color2,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Holistic Harmony",
        quote = "Health is holistic harmony; align your mind, body, and spirit, creating a symphony of well-being that resonates through every aspect of your life.",
        Author = "HarmonyHealer",
        AuthorDesignation = "Holistic Practitioner",
        color = color3,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Energy Elixir",
        quote = "Discover the energy elixir within; health is the potion that rejuvenates your being, restoring vitality and sparking the flame of well-being.",
        Author = "ElixirHealer",
        AuthorDesignation = "Energy Alchemist",
        color = color4,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Mindful Wellness",
        quote = "Wellness begins with mindfulness; cultivate awareness of your body's needs, nourishing it with the care it deserves for a life of vibrant health.",
        Author = "MindfulHealer",
        AuthorDesignation = "Wellness Mindfulness Guide",
        color = color5,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Radiant Health",
        quote = "Radiant health is your birthright; let your choices be the brushstrokes that paint a canvas of well-being, beauty, and vitality.",
        Author = "RadiantHealer",
        AuthorDesignation = "Wellness Artist",
        color = color6,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Balanced Bliss",
        quote = "Achieve balanced bliss; health is the equilibrium where physical, mental, and emotional well-being converge into a state of complete harmony.",
        Author = "BlissHealer",
        AuthorDesignation = "Wellness Harmonizer",
        color = color7,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Revitalize Life",
        quote = "Revitalize your life force; health is the energy that propels you forward, breathing vitality into every moment and experience.",
        Author = "RevitalizeHealer",
        AuthorDesignation = "Life Force Revitalizer",
        color = color8,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Healthy Habits",
        quote = "Cultivate healthy habits; they are the seeds that, when nurtured, grow into the flourishing garden of well-being and enduring health.",
        Author = "HabitsHealer",
        AuthorDesignation = "Wellness Gardener",
        color = color9,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Wellness Wisdom",
        quote = "Wisdom is the cornerstone of wellness; let your choices be guided by the knowledge that leads to a life of health, joy, and fulfillment.",
        Author = "WisdomHealer",
        AuthorDesignation = "Wellness Sage",
        color = color10,
        AuthorPic = R.drawable.l
    ))

    QuotesList.add(Quotes(
        title = "Healing Harmony",
        quote = "Harmony is the healer; let your life be a melody of balanced choices, restoring health and fostering a state of well-being.",
        Author = "HealingHarmony",
        AuthorDesignation = "Wellness Maestro",
        color = color1,
        AuthorPic = R.drawable.b
    ))

    QuotesList.add(Quotes(
        title = "Wholesome Life",
        quote = "Wellness is a wholesome life; nourish your body, mind, and spirit, cultivating a state of holistic health and enduring well-being.",
        Author = "WholesomeHealer",
        AuthorDesignation = "Life Nourisher",
        color = color2,
        AuthorPic = R.drawable.c
    ))

    QuotesList.add(Quotes(
        title = "Mind-Body Fusion",
        quote = "Fuse mind and body in harmony; health is the symphony where mental and physical well-being dance together, creating a vibrant and balanced life.",
        Author = "FusionHealer",
        AuthorDesignation = "Wellness Choreographer",
        color = color3,
        AuthorPic = R.drawable.d
    ))

    QuotesList.add(Quotes(
        title = "Wellness Waves",
        quote = "Ride the wellness waves; let your life be a journey of health, surfing on the currents of well-being and the tides of vitality.",
        Author = "WavesHealer",
        AuthorDesignation = "Wellness Surfer",
        color = color4,
        AuthorPic = R.drawable.e
    ))

    QuotesList.add(Quotes(
        title = "Vibrant Living",
        quote = "Live vibrantly; wellness is the palette with which you paint your life, creating a masterpiece of health, joy, and purpose.",
        Author = "VibrantHealer",
        AuthorDesignation = "Living Palette Artist",
        color = color5,
        AuthorPic = R.drawable.g
    ))

    QuotesList.add(Quotes(
        title = "Holistic Health",
        quote = "Health is holistic; let your choices embrace the interconnected web of physical, mental, and emotional well-being, creating a life of true health.",
        Author = "HolisticHealer",
        AuthorDesignation = "Wellness Weaver",
        color = color6,
        AuthorPic = R.drawable.h
    ))

    QuotesList.add(Quotes(
        title = "Energize Essence",
        quote = "Energize your essence; health is the vitality that flows through your being, bringing life to your every cell and radiance to your existence.",
        Author = "EssenceHealer",
        AuthorDesignation = "Vitality Energizer",
        color = color7,
        AuthorPic = R.drawable.i
    ))

    QuotesList.add(Quotes(
        title = "Renewal Rhythms",
        quote = "Embrace the renewal rhythms; health is the dance of well-being, where each step brings rejuvenation and every beat echoes vitality.",
        Author = "RhythmsHealer",
        AuthorDesignation = "Wellness Dancer",
        color = color8,
        AuthorPic = R.drawable.j
    ))

    QuotesList.add(Quotes(
        title = "Vital Essence",
        quote = "Tap into your vital essence; health is the life force that courses through your veins, fueling your journey with energy and well-being.",
        Author = "EssenceHealer",
        AuthorDesignation = "Vitality Tapper",
        color = color9,
        AuthorPic = R.drawable.k
    ))

    QuotesList.add(Quotes(
        title = "Wellness Wisdom",
        quote = "Wisdom guides wellness; let your choices be steeped in the knowledge that nurtures health, longevity, and a life well-lived.",
        Author = "WisdomHealer",
        AuthorDesignation = "Wellness Sage",
        color = color10,
        AuthorPic = R.drawable.l
    ))

    return QuotesList
}
fun HorrorQuotesList(): MutableList<Quotes> {
    val QuotesList = mutableListOf<Quotes>()

    QuotesList.add(
        Quotes(
            title = "Eerie Shadows",
            quote = "In the eerie shadows, fear lurks like a phantom, whispering chilling tales that send shivers down the spine of the bravest souls.",
            Author = "ShadowWhisperer",
            AuthorDesignation = "Horror Maestro",
            color = color1,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Dreadful Night",
            quote = "On this dreadful night, darkness weaves a tapestry of nightmares, and the air is thick with the unsettling presence of things unseen.",
            Author = "NightmareWeaver",
            AuthorDesignation = "Master of Dread",
            color = color2,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Chilling Whispers",
            quote = "Chilling whispers echo in the haunted halls, where ghostly tales unfold, and the spirits of the past awaken to share their spectral stories.",
            Author = "WhisperingSpecter",
            AuthorDesignation = "Ghost Storyteller",
            color = color3,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Ghastly Presence",
            quote = "Feel the ghastly presence that lingers in the shadows, as unseen eyes watch and unseen hands reach out from the ethereal realm of the unknown.",
            Author = "GhastlyWatcher",
            AuthorDesignation = "Spectral Observer",
            color = color4,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Eternal Fear",
            quote = "In the realm of eternal fear, nightmares are born, and every creak and moan becomes a symphony of terror that resonates through the haunted corridors.",
            Author = "FearConductor",
            AuthorDesignation = "Master of Nightmares",
            color = color5,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Dreadful Silence",
            quote = "In the dreadful silence, the echoes of fear reverberate, and the mind becomes a canvas where the imagination paints scenes of horror and suspense.",
            Author = "SilentTerror",
            AuthorDesignation = "Horror Artisan",
            color = color6,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
        Quotes(
            title = "Ephemeral Frights",
            quote = "Ephemeral frights dance in the moonlight, as the spirits of the unknown materialize, casting eerie shadows that play tricks on the senses.",
            Author = "MoonlightSpecter",
            AuthorDesignation = "Phantom Choreographer",
            color = color7,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Sinister Dreams",
            quote = "In the sinister dreams that haunt the sleeping mind, the boundaries between reality and nightmare blur, and the subconscious becomes a realm of terror.",
            Author = "DreamsOfDarkness",
            AuthorDesignation = "Nightmare Dreamer",
            color = color8,
            AuthorPic = R.drawable.j
        )
    )

    QuotesList.add(
        Quotes(
            title = "Macabre Symphony",
            quote = "A macabre symphony unfolds in the haunted mansion, where each creaking floorboard and ghostly whisper adds a chilling note to the spectral composition.",
            Author = "SymphonyOfShadows",
            AuthorDesignation = "Maestro of the Macabre",
            color = color9,
            AuthorPic = R.drawable.k
        )
    )

    QuotesList.add(
        Quotes(
            title = "Shivers of Dread",
            quote = "Shivers of dread crawl up the spine as the moonlight reveals the hidden terrors that thrive in the darkness, waiting to unleash their horrors.",
            Author = "DreadfulMoonlight",
            AuthorDesignation = "Shadow Unveiler",
            color = color10,
            AuthorPic = R.drawable.l
        )
    )

    // Additional Horror Quotes
    QuotesList.add(
        Quotes(
            title = "Phantom Fear",
            quote = "Fear, the phantom that haunts the corridors of the mind, whispers chilling secrets and conjures shadows that dance in the depths of darkness.",
            Author = "FearPhantom",
            AuthorDesignation = "Master of Shadows",
            color = color1,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Ghostly Gloom",
            quote = "In the ghostly gloom, specters of the past emerge, painting tales of sorrow and despair on the canvas of haunted memories.",
            Author = "GloomySpecter",
            AuthorDesignation = "Harbinger of Despair",
            color = color2,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Dark Descent",
            quote = "Embark on a dark descent into the abyss of fear, where the unknown awaits, and the shadows weave a tapestry of nightmares.",
            Author = "DescentIntoDarkness",
            AuthorDesignation = "Fearful Explorer",
            color = color3,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Spectral Shadows",
            quote = "Spectral shadows dance in the moonlight, casting an eerie ballet that tells the tale of the ethereal realm where spirits linger and specters roam.",
            Author = "MoonlitSpecter",
            AuthorDesignation = "Spectral Choreographer",
            color = color4,
            AuthorPic = R.drawable.j
        )
    )

    QuotesList.add(
        Quotes(
            title = "Eternal Nightmares",
            quote = "In the realm of eternal nightmares, the mind becomes a labyrinth of terror, and every twist and turn reveals a new horror to confront.",
            Author = "NightmareLabyrinth",
            AuthorDesignation = "Labyrinthian Dreamer",
            color = color5,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Whispers of the Damned",
            quote = "Hear the whispers of the damned; echoes of lost souls speak of torment and anguish, as shadows envelop the forsaken in eternal darkness.",
            Author = "DamnedWhisperer",
            AuthorDesignation = "Emissary of Desolation",
            color = color6,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Soul's Abyss",
            quote = "Plunge into the abyss of the soul, where fears take shape, and the ghosts of past traumas manifest in a haunting dance of unresolved anguish.",
            Author = "AbyssalSoul",
            AuthorDesignation = "Soulful Haunter",
            color = color7,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Phantom Melancholy",
            quote = "In the phantom melancholy, shadows weep, and the echoes of sorrow resonate through the haunted corridors, where despair takes on spectral form.",
            Author = "MelancholyPhantom",
            AuthorDesignation = "Sorrowful Specter",
            color = color8,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Cursed Whispers",
            quote = "Cursed whispers linger in the air, carrying the weight of malevolent secrets that unravel the threads of reality and plunge the mind into madness.",
            Author = "WhispersOfCurses",
            AuthorDesignation = "Bearer of Malevolence",
            color = color9,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Ephemeral Nightmares",
            quote = "Ephemeral nightmares visit in the dead of night, where the veil between dream and reality is thin, and the surreal becomes a waking horror.",
            Author = "NightmareWeaver",
            AuthorDesignation = "Dreamwalker of Dread",
            color = color10,
            AuthorPic = R.drawable.h
        )
    )

    return QuotesList
}
fun IslamicQuotesList(): MutableList<Quotes> {
    val QuotesList = mutableListOf<Quotes>()

    QuotesList.add(
        Quotes(
            title = "Divine Guidance",
            quote = "In the path of life, seek the divine guidance that lights the way and leads to a fulfilling journey guided by faith, wisdom, and compassion.",
            Author = "GuidingLight",
            AuthorDesignation = "Islamic Scholar",
            color = color1,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Prayerful Heart",
            quote = "A prayerful heart is a fortress of peace, where the soul finds solace in communion with the Almighty, and every supplication becomes a step towards serenity.",
            Author = "HeartfulPrayer",
            AuthorDesignation = "Believer in Unity",
            color = color2,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Faithful Steps",
            quote = "Walk the path of faith with firm and faithful steps, for in the journey of life, trust in the divine plan and let your heart find tranquility in surrender.",
            Author = "FaithWalker",
            AuthorDesignation = "Trustful Soul",
            color = color3,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Light of Hope",
            quote = "In the darkness of despair, let the light of hope guide your way. Trust in Allah's mercy, for even in the bleakest moments, His grace shines bright.",
            Author = "HopeBearer",
            AuthorDesignation = "Bearer of Light",
            color = color4,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Soulful Submission",
            quote = "Submit your soul to the divine will, for in surrendering to Allah, you find true freedom, peace, and a purpose that transcends the trials of this world.",
            Author = "SoulfulSubmission",
            AuthorDesignation = "Servant of the Divine",
            color = color5,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Quranic Wisdom",
            quote = "The Quran is a reservoir of timeless wisdom; let its verses be a source of guidance, solace, and inspiration on the journey of life.",
            Author = "QuranicSage",
            AuthorDesignation = "Bearer of Divine Words",
            color = color6,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
        Quotes(
            title = "Mercy of Allah",
            quote = "In the boundless mercy of Allah, find comfort and forgiveness. His compassion knows no bounds, and His love envelops every soul in grace.",
            Author = "MercifulGrace",
            AuthorDesignation = "Recipient of Divine Mercy",
            color = color7,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Prophetic Guidance",
            quote = "Follow the footsteps of the Prophet, embodying his teachings of compassion, humility, and righteousness, for in his example, find the path to true success.",
            Author = "GuidedByProphet",
            AuthorDesignation = "Follower of the Sunnah",
            color = color8,
            AuthorPic = R.drawable.j
        )
    )

    QuotesList.add(
        Quotes(
            title = "Faithful Reflection",
            quote = "Reflect upon your faith, for in contemplation, discover the deeper meanings of life. Let your heart resonate with the echoes of divine wisdom.",
            Author = "ReflectiveFaith",
            AuthorDesignation = "Seeker of Spiritual Insights",
            color = color9,
            AuthorPic = R.drawable.k
        )
    )

    QuotesList.add(
        Quotes(
            title = "Blessings of Gratitude",
            quote = "Gratitude is the key to unlocking the blessings of Allah. In every moment, express thanks, and watch as His divine favors shower upon the grateful heart.",
            Author = "GratefulServant",
            AuthorDesignation = "Bearer of Thankfulness",
            color = color10,
            AuthorPic = R.drawable.l
        )
    )

    // Additional Islamic Quotes
    QuotesList.add(
        Quotes(
            title = "Sacred Journey",
            quote = "Life is a sacred journey, and Islam is the guiding light that illuminates the path. Walk with faith, and Allah will be your constant companion.",
            Author = "SacredPilgrim",
            AuthorDesignation = "Traveler of the Faithful",
            color = color1,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Peaceful Heart",
            quote = "A heart at peace is a garden of serenity. Nurture it with prayers, kindness, and devotion, and watch as the flowers of tranquility bloom in abundance.",
            Author = "HeartfulPeace",
            AuthorDesignation = "Caretaker of Serenity",
            color = color2,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Divine Reflections",
            quote = "In the reflection of nature, see the signs of Allah's creation. The beauty of the world is a testament to His greatness and an invitation to contemplate His wonders.",
            Author = "NatureReflects",
            AuthorDesignation = "Observer of Divine Beauty",
            color = color3,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Soulful Remembrance",
            quote = "In the remembrance of Allah, find solace for the weary soul. Let the echoes of His name be a melody that soothes, uplifts, and brings inner peace.",
            Author = "RemembranceSoul",
            AuthorDesignation = "Soulful Devotee",
            color = color4,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Sacred Moments",
            quote = "Each moment is a sacred gift from Allah. Cherish it, for in the tapestry of time, every second is woven with His grace and the potential for spiritual growth.",
            Author = "MomentsOfGrace",
            AuthorDesignation = "Guardian of Time",
            color = color5,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Wisdom of Patience",
            quote = "Patience is a wisdom-filled virtue. In moments of trial, trust in Allah's plan, for in the tapestry of patience, discover the threads of strength and resilience.",
            Author = "PatienceWisdom",
            AuthorDesignation = "Bearer of Endurance",
            color = color6,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Radiance of Faith",
            quote = "Faith is a radiant light that dispels the darkness of doubt. Let it shine within, guiding your path and illuminating the way to eternal bliss.",
            Author = "FaithfulRadiance",
            AuthorDesignation = "Bearer of Spiritual Light",
            color = color7,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Eternal Connection",
            quote = "The bond with Allah is eternal and unbreakable. In prayer, feel the connection that transcends time and space, uniting the finite with the Infinite.",
            Author = "EternalConnection",
            AuthorDesignation = "Soul Linked to the Divine",
            color = color8,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Sacred Intentions",
            quote = "In every action, set sacred intentions. Whether big or small, let your deeds be offerings of love and obedience to the One who knows the secrets of the heart.",
            Author = "IntentionsOfLove",
            AuthorDesignation = "Conscious Worshipper",
            color = color9,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Guiding Stars",
            quote = "The Quran and Sunnah are guiding stars in the night sky of life. Let their light navigate your journey, steering you away from darkness and towards divine clarity.",
            Author = "GuidingStars",
            AuthorDesignation = "Navigator of Divine Wisdom",
            color = color10,
            AuthorPic = R.drawable.h
        )
    )
    // Additional Islamic Quotes
    QuotesList.add(
        Quotes(
            title = "Divine Harmony",
            quote = "Find harmony in surrendering to Allah's divine plan. In the symphony of life, let your heart resonate with the melodies of trust, peace, and contentment.",
            Author = "HarmonySeeker",
            AuthorDesignation = "Soul in Tune",
            color = color1,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Spiritual Journey",
            quote = "Life is a spiritual journey, and each trial is a stepping stone to greater closeness to Allah. Embrace the lessons, and let your faith be your guide.",
            Author = "JourneyOfFaith",
            AuthorDesignation = "Traveler of the Divine Path",
            color = color2,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Graceful Patience",
            quote = "Patience is a graceful virtue bestowed by Allah. In moments of waiting, let your heart be adorned with the beauty of endurance, trust, and graceful anticipation.",
            Author = "GracefulPatience",
            AuthorDesignation = "Bearer of Virtue",
            color = color3,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Mercy Unbounded",
            quote = "Allah's mercy knows no bounds. In times of repentance, let your tears be a testament to His compassion, and your heart, a vessel for His boundless forgiveness.",
            Author = "UnboundedMercy",
            AuthorDesignation = "Recipient of Divine Compassion",
            color = color4,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Eternal Gratitude",
            quote = "Gratitude is an offering to Allah. In every blessing, express thanks, and in every challenge, find the opportunity for gratitude that leads to spiritual growth.",
            Author = "EternalGratitude",
            AuthorDesignation = "Grateful Soul",
            color = color5,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Soulful Reflections",
            quote = "Reflect on the divine wisdom embedded in your journey. Let your heart be a mirror that reflects the lessons learned, the blessings received, and the path ahead.",
            Author = "ReflectiveSoul",
            AuthorDesignation = "Seeker of Inner Truths",
            color = color6,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
        Quotes(
            title = "Infinite Blessings",
            quote = "Allah's blessings are infinite. Let your heart be a vessel that overflows with gratitude, and may every breath be a reminder of His unending favors.",
            Author = "InfiniteBlessings",
            AuthorDesignation = "Bearer of Divine Gifts",
            color = color7,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Guiding Light",
            quote = "In the darkness of doubt, let your faith be the guiding light. Trust that Allah's plan is perfect, and in His light, find the clarity to navigate life's journey.",
            Author = "FaithfulGuidance",
            AuthorDesignation = "Bearer of Divine Light",
            color = color8,
            AuthorPic = R.drawable.j
        )
    )

    QuotesList.add(
        Quotes(
            title = "Peaceful Surrender",
            quote = "True peace is found in surrender to Allah. Let your heart be a sanctuary of serenity, where trust in His wisdom brings tranquility in every circumstance.",
            Author = "PeacefulSurrender",
            AuthorDesignation = "Seeker of Inner Peace",
            color = color9,
            AuthorPic = R.drawable.k
        )
    )

    QuotesList.add(
        Quotes(
            title = "Divine Love",
            quote = "In the garden of the heart, let divine love blossom. Water it with prayers, nourish it with good deeds, and watch as it blooms into a fragrant bouquet of spiritual bliss.",
            Author = "LoveInBloom",
            AuthorDesignation = "Caretaker of the Heart's Garden",
            color = color10,
            AuthorPic = R.drawable.l
        )
    )


    return QuotesList
}
fun LeadershipQuotesList(): MutableList<Quotes> {
    val QuotesList = mutableListOf<Quotes>()

    QuotesList.add(
        Quotes(
            title = "Visionary Leadership",
            quote = "A visionary leader paints a compelling picture of the future, inspiring others to join the journey towards a shared vision that transcends challenges and transforms aspirations into reality.",
            Author = "VisionaryLeader",
            AuthorDesignation = "Architect of Tomorrow",
            color = color1,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Inspirational Influence",
            quote = "True leadership is not about authority but about inspirational influence. Lead with integrity, empathy, and a genuine desire to uplift others, and your impact will be everlasting.",
            Author = "InspirationalInfluence",
            AuthorDesignation = "Catalyst of Change",
            color = color2,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Empowering Teams",
            quote = "Empowerment is the cornerstone of effective leadership. Nurture an environment where each team member's potential is recognized, valued, and cultivated for collective success.",
            Author = "TeamEmpowerer",
            AuthorDesignation = "Empowerment Advocate",
            color = color3,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Resilient Leadership",
            quote = "In the face of adversity, a resilient leader rises like a phoenix, demonstrating strength, adaptability, and an unwavering commitment to navigate challenges and emerge stronger.",
            Author = "ResilientLeader",
            AuthorDesignation = "Master of Resilience",
            color = color4,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Servant Leadership",
            quote = "A true leader is a servant first, dedicated to serving the needs of others. By lifting others, a leader creates a culture of collaboration, trust, and shared success.",
            Author = "ServantLeader",
            AuthorDesignation = "Champion of Service",
            color = color5,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Innovative Leadership",
            quote = "Innovation is the heartbeat of progress. A leader fosters a culture of creativity, embracing change, and inspiring the team to explore new horizons with courage and curiosity.",
            Author = "InnovativeVisionary",
            AuthorDesignation = "Pioneer of Progress",
            color = color6,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
        Quotes(
            title = "Strategic Leadership",
            quote = "Strategic leadership is the art of navigating complexity with a clear roadmap. A leader's foresight and decisiveness pave the way for the team's success in uncharted territories.",
            Author = "StrategicNavigator",
            AuthorDesignation = "Strategist Extraordinaire",
            color = color7,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Authentic Leadership",
            quote = "Authenticity is the cornerstone of impactful leadership. By embracing one's true self, a leader fosters trust, connection, and a culture where authenticity is celebrated.",
            Author = "AuthenticLeader",
            AuthorDesignation = "Champion of Authenticity",
            color = color8,
            AuthorPic = R.drawable.j
        )
    )

    QuotesList.add(
        Quotes(
            title = "Ethical Leadership",
            quote = "Ethical leadership is a moral compass guiding decisions and actions. A leader's commitment to integrity, fairness, and responsibility sets the standard for the entire team.",
            Author = "EthicalGuardian",
            AuthorDesignation = "Keeper of Integrity",
            color = color9,
            AuthorPic = R.drawable.k
        )
    )

    QuotesList.add(
        Quotes(
            title = "Transformational Leadership",
            quote = "Transformational leaders inspire change and growth, challenging the status quo and empowering others to reach their full potential in pursuit of a shared and meaningful vision.",
            Author = "TransformationalGuide",
            AuthorDesignation = "Architect of Change",
            color = color10,
            AuthorPic = R.drawable.l
        )
    )

    // Additional Leadership Quotes
    QuotesList.add(
        Quotes(
            title = "Courageous Leadership",
            quote = "Courage is the cornerstone of leadership. A courageous leader faces challenges head-on, empowers others to do the same, and fosters a culture of fearlessness.",
            Author = "CourageousLeader",
            AuthorDesignation = "Fearless Pioneer",
            color = color1,
            AuthorPic = R.drawable.b
        )
    )

    QuotesList.add(
        Quotes(
            title = "Adaptive Leadership",
            quote = "Adaptability is a mark of exceptional leadership. In a dynamic world, a leader navigates change with grace, learning from each experience and guiding the team to new heights.",
            Author = "AdaptiveNavigator",
            AuthorDesignation = "Master of Adaptation",
            color = color2,
            AuthorPic = R.drawable.c
        )
    )

    QuotesList.add(
        Quotes(
            title = "Leadership Legacy",
            quote = "A leader's legacy is written in the impact on others. Lead with purpose, leaving a legacy of inspiration, empowerment, and positive change for future generations.",
            Author = "LegacyBuilder",
            AuthorDesignation = "Architect of Enduring Influence",
            color = color3,
            AuthorPic = R.drawable.d
        )
    )

    QuotesList.add(
        Quotes(
            title = "Collaborative Leadership",
            quote = "Collaboration is the heartbeat of effective leadership. A leader unites diverse strengths, encourages open communication, and fosters a culture where collective success is celebrated.",
            Author = "CollaborativeHarmonizer",
            AuthorDesignation = "Maestro of Unity",
            color = color4,
            AuthorPic = R.drawable.e
        )
    )

    QuotesList.add(
        Quotes(
            title = "Leadership Gratitude",
            quote = "Gratitude is the hallmark of a great leader. Acknowledge the contributions of others, express appreciation, and create a culture where gratitude fosters growth and unity.",
            Author = "GratefulLeader",
            AuthorDesignation = "Bearer of Thankfulness",
            color = color5,
            AuthorPic = R.drawable.g
        )
    )

    QuotesList.add(
        Quotes(
            title = "Inspired Leadership",
            quote = "An inspired leader ignites the flame of passion in others, fostering a sense of purpose and dedication that transcends challenges and transforms visions into reality.",
            Author = "InspiredVisionary",
            AuthorDesignation = "Igniter of Passion",
            color = color6,
            AuthorPic = R.drawable.h
        )
    )

    QuotesList.add(
        Quotes(
            title = "Diversity in Leadership",
            quote = "Diversity is the strength of leadership. Embrace differences, foster inclusion, and lead with a commitment to understanding and appreciating the richness of diverse perspectives.",
            Author = "DiversityChampion",
            AuthorDesignation = "Advocate of Inclusion",
            color = color7,
            AuthorPic = R.drawable.i
        )
    )

    QuotesList.add(
        Quotes(
            title = "Admirable Leadership",
            quote = "An admirable leader inspires admiration not only through achievements but also through humility, empathy, and a commitment to the well-being and success of others.",
            Author = "AdmirableLeader",
            AuthorDesignation = "Exemplar of Humility",
            color = color8,
            AuthorPic = R.drawable.j
        )
    )

    QuotesList.add(
        Quotes(
            title = "Leadership Wisdom",
            quote = "Wisdom is the compass of leadership. A wise leader listens, learns, and guides with discernment, bringing clarity, insight, and a steady hand in every decision.",
            Author = "WiseNavigator",
            AuthorDesignation = "Bearer of Leadership Wisdom",
            color = color9,
            AuthorPic = R.drawable.k
        )
    )

    QuotesList.add(
        Quotes(
            title = "Leadership Impact",
            quote = "A leader's impact is measured not by personal success alone but by the positive influence on the lives and growth of others. Lead with a heart that seeks to uplift and empower.",
            Author = "ImpactfulLeader",
            AuthorDesignation = "Architect of Positive Change",
            color = color10,
            AuthorPic = R.drawable.l
        )
    )

    return QuotesList
}


