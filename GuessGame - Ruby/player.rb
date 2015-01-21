class Player
  include Comparable
  attr_reader :name,:oracle,:results,:range,:number_of_guesses
  def initialize name,oracle,range
    @name = name
    @range = range
    @oracle = oracle
    @results = 0
    @number_of_guesses = 0
  end
  def guess
    @number_of_guesses+=1
  end
  def <=> player
    player.number_of_guesses <=>@number_of_guesses
  end
  def to_s
    @results == 0 ? "#{@name} guessed #{@guess} and won!" : "#{@name} guessed #{@guess}"
  end
end