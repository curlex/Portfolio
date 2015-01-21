require_relative 'guess_it'
guess_it = GuessIt.new 'John','Aoife','Mary'
guess_it.set_up_game
guess_it.sort!
puts guess_it.to_s