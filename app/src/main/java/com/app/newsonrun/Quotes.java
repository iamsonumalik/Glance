package com.app.newsonrun;

/**
 * Created by malik on 1/2/16.
 */
public class Quotes {

    String []quoteslist;
    String []autherlist;

    Quotes(){
        quoteslist = new String[]{
                "Wanting to be someone else is a waste of who you are.",
                "If things go wrong, don't go with them.",
                "I will not let anyone walk through my mind with their dirty feet.",
                "Whenever you find yourself on the side of the majority, it is time to pause and reflect.",
                "Do what you feel in your heart to be right, for you'll be criticized anyway.",
                "Do your own thing on your own terms and get what you came here for.",
                "Imitation is suicide.",
                "Be who you are and say what you feel, because those who mind don't matter and those who matter don't mind.",
                "Better to write for yourself and have no public, than to write for the public and have no self.",
                "We must not allow other people's limited perceptions to define us.",
                "Don't look for society to give you permission to be yourself.",
                "Tension is who you think you should be. Relaxation is who you are.",
                "Where's your will to be weird?",
                "Do what you can, with what you have, where you are.",
                "Be yourself everyone else is already taken.",
                "There came a time when the risk to remain tight in the bud was more painful than the risk it took to blossom.",
                "To find yourself, think for yourself.",
                "If you cannot be a poet, be the poem.",
                "Be there for others, but never leave yourself behind.",
                "Do what you must, And your friends will adjust.",
                "Just let awareness have its way with you completely.",
                "We must be our own before we can be another's.",
                "Resist much, obey little.",
                "Never be afraid to fall apart because it is an opportunity to rebuild yourself the way you wish you had been all along.",
                "Life is pure adventure, and the sooner we realise that, the quicker we will be able to treat life as art.",
                "Appreciate the moment of a first kiss; it may be the last time you own your heart.",
                "Admire someone else's beauty without questioning your own.",
                "The only way out of the labyrinth of suffering is to forgive.",
                "In a time of deceit telling the truth is a revolutionary act.",
                "Nothing haunts us like the things we don't say.",
                "The best books' are those that tell you what you know already.",
                "The quickest way to get someone's attention is to no longer want it.",
                "Maybe our favorite quotations say more about us than about the stories and people we're quoting.",
                "What is the point of being alive if you don't at least try to do something remarkable?",
                "You were born with wings, why prefer to crawl through life.",
                "What you seek is seeking you.",
                "An intellectual says a simple thing in a hard way. An artist says a hard thing in a simple way.",
                "All that we see or seem is but a dream within a dream.",
                "Raise your words, not your voice. It is rain that grows flowers, not thunder.",
                "Find ecstasy in life; the mere sense of living is joy enough.",
                "Two roads diverged in a wood and I - I took the one less traveled by, and that has made all the difference.",
                "A mother takes twenty years to make a man of her boy, and another woman makes a fool of him in twenty minutes.",
                "Our greatest glory is not in never failing, but in rising up every time we fail.",
                "To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment.",
                "Love recognizes no barriers. It jumps hurdles, leaps fences, penetrates walls to arrive at its destination full of hope.",
                "Forever is composed of nows.",
                "It's no wonder that truth is stranger than fiction. Fiction has to make sense.",
                "They say the most romantic kind of love is the unfinished kind. The kind that will forever burn and mark your soul.",
                "When I am silent, I have thunder hidden inside."

        };

        autherlist = new String[]{
                "Kurt Cobain",
                "Roger Babson",
                "Mahatma Gandhi",
                "Mark Twain",
                "Eleanor Roosevelt",
                "Oliver James",
                "Ralph Waldo Emerson",
                "Dr. Seuss",
                "Cyril Connolly",
                "Virginia Satir",
                "Steve Maraboli",
                "Chinese Proverb",
                "Jim Morrison",
                "Theodore Roosevelt",
                "Oscar Wilde",
                "Anaïs Nin",
                "Socrates",
                "David Carradine",
                "Dodinsky,",
                "Robert Brault",
                "Scott Morrison",
                "Ralph Waldo Emerson",
                "Walt Whitman",
                "Rae Smith",
                "Maya Angelou",
                "Robert M. Drake",
                "Unknown",
                "John Green",
                "George Orwell",
                "Mitch Albom",
                "George Orwell",
                "Anonymous",
                "John Green",
                "John Green",
                "Rumi",
                "Rumi",
                "Charles Bukowski",
                "Edgar Allan Poe",
                "Rumi",
                "Emily Dickinson",
                "Robert Frost",
                "Robert Frost",
                "Ralph Waldo Emerson",
                "Ralph Waldo Emerson",
                "Maya Angelou",
                "Emily Dickinson",
                "Mark Twain",
                "Pamela Ann",
                "Rumi"

        };

    }

    public String getQuoteslist(int pos) {
        return quoteslist[pos];
    }

    public String getAutherlist(int pos) {
        return autherlist[pos];
    }
}
